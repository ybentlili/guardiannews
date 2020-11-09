package com.test.guardiannews.ui.articles

import com.test.guadiannews.domain.model.Article

interface ListArticlesListener {
    fun onItemClicked(article: Article)
}