package com.test.guardiannews.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.test.guardiannews.R
import com.test.guardiannews.base.ViewRender
import com.test.guardiannews.constant.API_KEY
import com.test.guardiannews.constant.ID_ARTICLE
import com.test.guardiannews.constant.SHOW_FIELD_DETAILS
import com.test.guardiannews.databinding.DetailsArticleLayoutBinding
import com.test.guardiannews.extentions.getTimeFromBasicFormat
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsArticleActivity : AppCompatActivity() , ViewRender<ArticleDetailsPartialState> {

    private val  articleDetailsViewModel : ArticleDetailsViewModel by viewModel()
    private var  binding : DetailsArticleLayoutBinding?  = null
    private var currentIdArticle :String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailsArticleLayoutBinding.inflate(layoutInflater)
        setContentView(binding?.clRoot)

        intent?.extras?.getString(ID_ARTICLE)?.let {
            currentIdArticle = it
            initView()
        }
    }

    private fun initView(){
        articleDetailsViewModel.dispatchIntent(ArticleDetailsIntent.GetArticleDetails(API_KEY, SHOW_FIELD_DETAILS, currentIdArticle))
        articleDetailsViewModel.state.observe(this, {render(it)})
        binding?.ivBack?.setOnClickListener {
            finish()
        }
    }

    override fun render(state: ArticleDetailsPartialState) {
        when(state){
            is ArticleDetailsPartialState.ArticleDetailsSuccessufly -> {
                binding?.tvLoadingData?.visibility = View.GONE
                Glide.with(this).load(state.detailArticle.urlImage).into(binding?.ivArticle!!)
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    binding?.tvContentArticle?.text = Html.fromHtml(state.detailArticle.filedBody,Html.FROM_HTML_MODE_LEGACY)
                    binding?.tvMainArticle?.text = Html.fromHtml(state.detailArticle.filedMain,Html.FROM_HTML_MODE_LEGACY)
                    binding?.tvHeadlineArticle?.text = Html.fromHtml(state.detailArticle.filedHeadline,Html.FROM_HTML_MODE_LEGACY)
                    binding?.tvContentArticle?.movementMethod  = android.text.method.LinkMovementMethod.getInstance()
                } else {
                   binding?.tvContentArticle?.text = (Html.fromHtml(state.detailArticle.filedBody))
                    binding?.tvMainArticle?.text = (Html.fromHtml(state.detailArticle.filedMain));
                    binding?.tvHeadlineArticle?.text = (Html.fromHtml(state.detailArticle.filedHeadline));
                    binding?.tvContentArticle?.movementMethod  = android.text.method.LinkMovementMethod.getInstance()
                }
                binding?.tvDateArticle?.text = state.detailArticle.publishDate?.getTimeFromBasicFormat()
                articleDetailsViewModel.dispatchIntent(ArticleDetailsIntent.ConsumeGetArticleDetails)

            }
            is ArticleDetailsPartialState.GetArticleDetailsLoading -> {
               Log.i("TAG","LOADING")
                binding?.tvLoadingData?.visibility = View.VISIBLE
            }
            is ArticleDetailsPartialState.GetArticleDetailsError -> {
                Log.e("TAG", "Error ${state.message}")
                Snackbar.make(binding?.clRoot as View, resources.getString(R.string.error), Snackbar.LENGTH_SHORT)
                        .show()
                binding?.tvLoadingData?.visibility = View.GONE
                articleDetailsViewModel.dispatchIntent(ArticleDetailsIntent.ConsumeGetArticleDetails)
            }
            is ArticleDetailsPartialState.EventDetailsArticleConsumed -> {
            }
        }

    }
}