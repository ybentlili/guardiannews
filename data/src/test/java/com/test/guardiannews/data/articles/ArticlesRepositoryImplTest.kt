package com.test.guardiannews.data.articles

import com.test.guadiannews.domain.model.Article
import com.test.guadiannews.domain.model.ListArticle
import com.test.guardiannews.data.datasource.NetworkDataSource
import com.test.guardiannews.data.model.articles.FiledNewsData
import com.test.guardiannews.data.model.articles.ListNews
import com.test.guardiannews.data.model.articles.NewsData
import com.test.guardiannews.data.model.articles.ResponseListNewsData
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.internal.verification.VerificationModeFactory
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ArticlesRepositoryImplTest {

    private val networkDataSource = Mockito.mock(NetworkDataSource::class.java)
    private val apiKey : String = "b910f9e7-183e-4041-893c-76456b317c44"

    private val showField : String = "headline,thumbnail"
    private val pageSize  : Int = 50
    private val query  : String = "football"
    private lateinit var articlesRepositoryImpl : ArticlesRepositoryImpl

    private var filedNewsData = FiledNewsData("thumbnail")
    private var newData = NewsData("id","type","webTitle","sectionName","webPublicationDate",filedNewsData )
    private var listNews = ListNews(1,1,1, listOf(newData))
    private var responseListNewsData = ResponseListNewsData(listNews)
    private var listArticle = ListArticle(1,1,listOf(Article("id","sectionName","webTitle",filedNewsData.thumbnail, "webPublicationDate")))

    @Before
    fun initTest(){
       articlesRepositoryImpl  = ArticlesRepositoryImpl(networkDataSource)
    }

    @Test
    fun testGetListArticle() {

        Mockito.`when`(networkDataSource.getAllArticles(apiKey, showField, pageSize, query)).thenReturn(
            Observable.just(responseListNewsData))

        var testObservable = TestObserver<ListArticle>()
        var resultObservable = articlesRepositoryImpl.getListArticle(apiKey, showField, pageSize, query)
        resultObservable.subscribe(testObservable)

        testObservable.assertComplete()
        testObservable.assertNoErrors()
        Assertions.assertThat(testObservable.values()).containsExactly(listArticle)
        Mockito.verify(networkDataSource, VerificationModeFactory.times(1))
            .getAllArticles(apiKey, showField, pageSize, query)
        Mockito.verifyNoMoreInteractions(networkDataSource)

    }
}