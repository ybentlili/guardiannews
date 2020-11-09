package com.test.guadiannews.domain.articles

import io.reactivex.Observable
import com.test.guadiannews.domain.model.ListArticle

interface ArticlesRepository {

    fun getListArticle(apiKey:String, showField :String, pageSize :Int, query :String) : Observable<ListArticle>
}