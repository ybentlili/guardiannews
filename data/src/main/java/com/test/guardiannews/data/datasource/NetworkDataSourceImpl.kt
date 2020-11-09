package com.test.guardiannews.data.datasource

import io.reactivex.Observable
import com.test.guardiannews.data.GuardianNetworkAPI
import com.test.guardiannews.data.model.article.ResponseDetailArticle
import com.test.guardiannews.data.model.articles.ResponseListNewsData

class NetworkDataSourceImpl(private val guardianNetworkAPI: GuardianNetworkAPI) :
    NetworkDataSource {

    override fun getAllArticles(apiKey: String, showField: String, pageSize: Int, query: String):
            Observable<ResponseListNewsData> =
             guardianNetworkAPI.getAllNews(apiKey, showField, pageSize, query)

    override fun getDetailsArticle(
        apiKey: String,
        showField: String,
        idArticle: String
    ): Observable<ResponseDetailArticle> =
        guardianNetworkAPI.getDetailsArticle(idArticle,apiKey, showField)
}

