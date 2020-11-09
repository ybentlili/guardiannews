package com.test.guardiannews.ui.articles

sealed class ArticlesIntent {
    // Intent to get all articles
    data class GetListArticles (
        var apiKey: String,
        var showField: String,
        var pageSize: Int,
        var query: String
    ): ArticlesIntent()
    // Intent to specify that the get list is finished
    object NotifyGetListArticlesExecuted : ArticlesIntent()
}