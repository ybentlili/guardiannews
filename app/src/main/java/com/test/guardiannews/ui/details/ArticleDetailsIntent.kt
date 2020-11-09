package com.test.guardiannews.ui.details

sealed class ArticleDetailsIntent {
    data class GetArticleDetails (
        var apiKey: String,
        var showField: String,
        var idArticle: String
    ): ArticleDetailsIntent()
    object ConsumeGetArticleDetails : ArticleDetailsIntent()
}