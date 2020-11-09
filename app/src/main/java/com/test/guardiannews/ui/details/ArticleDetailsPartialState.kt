package com.test.guardiannews.ui.details

import com.test.guadiannews.domain.model.DetailArticle
import com.test.guadiannews.domain.model.ListArticle


sealed class ArticleDetailsPartialState {

    data class ArticleDetailsSuccessufly(val detailArticle: DetailArticle) : ArticleDetailsPartialState() {
        val isLoading = false
        val isError = false
    }

    data class GetArticleDetailsLoading (val isLoading : Boolean , val isError : Boolean): ArticleDetailsPartialState() {
        val detailArticle = null
    }

    data class GetArticleDetailsError(val message: String?) : ArticleDetailsPartialState() {
        val isLoading = false
        val isError = true
        val event = message
    }
    object EventDetailsArticleConsumed : ArticleDetailsPartialState()
}