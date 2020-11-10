package com.test.guardiannews.ui.articles

import com.test.guadiannews.domain.model.ListArticle


sealed class ArticlesPartialState {

    data class ListArticlesSuccessfully(val list : ListArticle) : ArticlesPartialState() {
        val isLoading = false
    }

    data class ListArticlesLoading (val isLoading : Boolean , val isError : Boolean): ArticlesPartialState() {
        val articles = null
    }

    data class ListArticlesError(val message: String?) : ArticlesPartialState()
    object EventListArticlesConsumed : ArticlesPartialState()
}