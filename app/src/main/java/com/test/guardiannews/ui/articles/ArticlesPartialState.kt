package com.test.guardiannews.ui.articles

import com.test.guadiannews.domain.model.ListArticle


sealed class ArticlesPartialState {

    data class ListArticlesSuccessufly(val list : ListArticle) : ArticlesPartialState() {
        val isLoading = false
        val isError = false
    }

    data class ListArticlesLoading (val isLoading : Boolean , val isError : Boolean): ArticlesPartialState() {
        val articles = null
    }

    data class ListArticlesError(val message: String?) : ArticlesPartialState() {
        val isLoading = false
        val isError = true
        val event = message
    }
    object EventListArticlesConsumed : ArticlesPartialState()
}