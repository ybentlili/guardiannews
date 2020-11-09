package com.test.guardiannews.data.articles

import com.test.guadiannews.domain.article.ArticleRepository
import com.test.guadiannews.domain.model.DetailArticle
import com.test.guardiannews.data.datasource.NetworkDataSource
import io.reactivex.Observable

class ArticleRepositoryImpl(private val networkDataSource: NetworkDataSource) :
    ArticleRepository {

    override fun getDetailsArticle(apiKey: String, showField: String, idArticle: String) :Observable<DetailArticle> =
        networkDataSource.getDetailsArticle(apiKey, showField, idArticle).map {
            DetailArticle(
                sectionName = it.response.contentArticle.sectionName,
                title = it.response.contentArticle.webTitle,
                urlImage = it.response.contentArticle.fields.thumbnail,
                publishDate = it.response.contentArticle.webPublicationDate,
                filedMain = it.response.contentArticle.fields.main,
                filedBody = it.response.contentArticle.fields.body,
                filedHeadline = it.response.contentArticle.fields.headline
            )
        }
}