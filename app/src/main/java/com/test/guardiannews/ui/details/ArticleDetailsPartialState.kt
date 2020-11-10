package com.test.guardiannews.ui.details

import com.test.guadiannews.domain.model.DetailArticle


sealed class ArticleDetailsPartialState {

    data class ArticleDetailsSuccessfully(val detailArticle: DetailArticle) : ArticleDetailsPartialState()

    data class GetArticleDetailsLoading (val isLoading : Boolean , val isError : Boolean): ArticleDetailsPartialState()

    data class GetArticleDetailsError(val message: String?) : ArticleDetailsPartialState()
    object EventDetailsArticleConsumed : ArticleDetailsPartialState()
}