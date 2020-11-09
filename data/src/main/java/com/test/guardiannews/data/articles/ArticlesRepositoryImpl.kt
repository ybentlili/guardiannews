package com.test.guardiannews.data.articles

import com.test.guadiannews.domain.articles.ArticlesRepository
import com.test.guadiannews.domain.model.Article
import com.test.guadiannews.domain.model.ListArticle
import com.test.guardiannews.data.datasource.NetworkDataSource
import io.reactivex.Observable

class ArticlesRepositoryImpl(private val networkDataSource: NetworkDataSource) :
    ArticlesRepository {

    override fun getListArticle( apiKey: String,
                                 showField: String,
                                 pageSize: Int,
                                 query: String): Observable<ListArticle> =
         networkDataSource.getAllArticles(apiKey, showField,pageSize,query)
             .map {
                ListArticle(
                    total =  it.response.total,
                    numberPage = it.response.pages,
                    listArticle = it.response.listNews.map {
                        Article(id = it?.id,
                                sectionName = it.sectionName,
                                title = it.webTitle,
                                publishDate = it.webPublicationDate,
                                urlImage = it.fields?.thumbnail
                        )
                    }.toList()
            )
        }


}