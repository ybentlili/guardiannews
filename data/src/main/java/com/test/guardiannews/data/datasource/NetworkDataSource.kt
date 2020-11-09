package com.test.guardiannews.data.datasource

import com.test.guardiannews.data.model.article.ResponseDetailArticle
import io.reactivex.Observable
import com.test.guardiannews.data.model.articles.ResponseListNewsData

interface NetworkDataSource {
    fun getAllArticles(apiKey:String, showField :String, pageSize :Int, query :String) : Observable<ResponseListNewsData>
    fun getDetailsArticle (apiKey:String, showField :String, idArticle :String) : Observable<ResponseDetailArticle>

}