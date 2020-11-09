package com.test.guardiannews.ui.details

sealed class ArticleDetailAction {
    data class GetArticleDetails(var apiKey: String,
                               var showField: String,
                               var idArticle: String) : ArticleDetailAction()
    object ConsumeGetArticleDetails : ArticleDetailAction()
}