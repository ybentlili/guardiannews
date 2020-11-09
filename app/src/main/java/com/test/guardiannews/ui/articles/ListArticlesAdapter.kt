package com.test.guardiannews.ui.articles

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.guadiannews.domain.model.Article
import com.test.guardiannews.R
import com.test.guardiannews.databinding.ItemNewsBinding
import com.test.guardiannews.extentions.getDateFromBasicFormat

class ListArticlesAdapter (
        private val context: Context,
        private val onItemClicked : (Article) -> Unit
        ) : RecyclerView.Adapter<ListArticlesAdapter.ArticleViewHolder>() {

    private var articleList = emptyList<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder =
        ArticleViewHolder(LayoutInflater.from(context).inflate(R.layout.item_news,parent,false))

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(
            articleList.get(position),
            context
        ) { onItemClicked(articleList.get(position)) }
    }

    fun updateData(newsList: List<Article>) {
        this.articleList = newsList
        notifyDataSetChanged()
    }

    override fun getItemCount() = articleList.size


    class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemNewsBinding.bind(view)

        fun bind(article: Article, context: Context,
                listenerClickItem : (Article) -> Unit) {
            with(binding) {
               tvTitleNews.text = article.title
                tvDateNews.text = article.publishDate?.getDateFromBasicFormat()
                cvChildRoot.setOnClickListener { listenerClickItem(article)}
                Glide.with(context).load(article.urlImage).into(ivThumb)
            }
        }


    }



}