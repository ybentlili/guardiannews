package com.test.guardiannews.ui.articles

sealed class ArticlesAction {
    // Action to get all articles
    data class GetListArticles(var apiKey: String,
                               var showField: String,
                               var pageSize: Int,
                               var query: String) : ArticlesAction()
    // Action to specify that the get list is finished
    object ConsumeGetListArticles : ArticlesAction()
}