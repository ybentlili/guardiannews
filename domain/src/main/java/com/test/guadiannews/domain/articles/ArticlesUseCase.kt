package com.test.guadiannews.domain.articles

import com.test.guadiannews.domain.model.ListArticle
import io.reactivex.Observable

class ArticlesUseCase(private val articlesRepository: ArticlesRepository) {
    fun getAllArticles(apiKey: String,
                       showField: String,
                       pageSize: Int,
                       query: String) : Observable<ListArticle> = articlesRepository.getListArticle(apiKey,showField, pageSize, query)
}