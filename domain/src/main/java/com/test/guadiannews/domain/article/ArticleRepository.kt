package com.test.guadiannews.domain.article

import com.test.guadiannews.domain.model.DetailArticle
import io.reactivex.Observable

interface ArticleRepository {
    fun getDetailsArticle(apiKey:String, showField :String, idArticle :String) : Observable<DetailArticle>
}