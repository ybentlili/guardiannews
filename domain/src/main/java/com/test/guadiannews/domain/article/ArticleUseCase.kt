package com.test.guadiannews.domain.article

import com.test.guadiannews.domain.model.DetailArticle
import io.reactivex.Observable

class ArticleUseCase (private val articleRepository: ArticleRepository){

    fun getDetailsArticle (apiKey:String, showField :String, idArticle :String) :  Observable<DetailArticle> =
            articleRepository.getDetailsArticle(apiKey, showField, idArticle)
}
