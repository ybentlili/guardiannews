package com.test.guardiannews.ui.articles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.test.guadiannews.domain.model.Article
import com.test.guardiannews.R
import com.test.guardiannews.base.ViewRender
import com.test.guardiannews.constant.*
import com.test.guardiannews.databinding.ListNewsBinding
import com.test.guardiannews.ui.details.DetailsArticleActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListArticlesActivity : AppCompatActivity() , ViewRender<ArticlesPartialState> {

    private val listArticleViewModel : ListArticleViewModel by viewModel()
    private var  binding : ListNewsBinding?  = null
    private lateinit var listArticlesAdapter : ListArticlesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = ListNewsBinding.inflate(layoutInflater)
         setContentView(binding?.clRoot)
        initView()
        initViewModel()
    }

    /**
     * Init View Model
     */
    private fun initViewModel() {
        listArticleViewModel.dispatchIntent(
                ArticlesIntent.GetListArticles(
                        API_KEY, SHOW_FIELD,
                        PAGE_SIZE, QUERY
                )
        )
        listArticleViewModel.state.observe(this, { render(it) })
    }

    /**
     * Init Display View
     */
    private fun initView() {
        with(binding?.rvListNews){
            this?.adapter = ListArticlesAdapter(applicationContext,{onArticleClickedItem(it)})
            this?.layoutManager = LinearLayoutManager(applicationContext)
        }
        listArticlesAdapter = binding?.rvListNews?.adapter as ListArticlesAdapter
        binding?.swToRListNews?.setOnRefreshListener {
            listArticleViewModel.dispatchIntent(
                    ArticlesIntent.GetListArticles(
                            API_KEY, SHOW_FIELD,
                            PAGE_SIZE, QUERY
                    )
            )
        }
    }

    /**
     * Click to an item
     */
    private fun onArticleClickedItem(article :Article) {
        var intent = Intent(this, DetailsArticleActivity::class.java)
        intent.putExtra(ID_ARTICLE, article.id)
        startActivity(intent)

    }

    /**
     * Manage status of Get List Articles
     */

    override fun render(state: ArticlesPartialState) {
        when(state){
            is ArticlesPartialState.ListArticlesLoading -> {
                Log.i("TAG", "Application is loading")
                binding?.swToRListNews?.isRefreshing = state.isLoading
                binding?.rvListNews?.visibility = View.GONE
            }
            is ArticlesPartialState.ListArticlesSuccessfully -> {
                Log.i("TAG", "Application is loading : ${state.list.listArticle.size}")
                listArticlesAdapter.updateData(state.list.listArticle)
                binding?.swToRListNews?.isRefreshing = state.isLoading
                binding?.rvListNews?.visibility = View.VISIBLE
                listArticleViewModel.dispatchIntent(ArticlesIntent.NotifyGetListArticlesExecuted)
            }
            is ArticlesPartialState.ListArticlesError -> {
                Log.i("TAG","Application error : ${state.message}")
                binding?.swToRListNews?.isRefreshing = false
                binding?.rvListNews?.visibility = View.GONE
                Snackbar.make(binding?.clRoot as View, resources.getString(R.string.error), Snackbar.LENGTH_SHORT)
                        .show()
                listArticleViewModel.dispatchIntent(ArticlesIntent.NotifyGetListArticlesExecuted)
            }
            is ArticlesPartialState.EventListArticlesConsumed -> {
                Log.i("TAG","Load data is finish")
            }
        }

    }
}