package com.test.guardiannews.data.articles

import com.test.guadiannews.domain.model.DetailArticle
import com.test.guardiannews.data.datasource.NetworkDataSource
import com.test.guardiannews.data.model.article.ContentArticle
import com.test.guardiannews.data.model.article.ContentDataArticle
import com.test.guardiannews.data.model.article.FieldDetailsArticle
import com.test.guardiannews.data.model.article.ResponseDetailArticle
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import junit.framework.TestCase
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.internal.verification.VerificationModeFactory
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ArticleRepositoryImplTest : TestCase() {

    private val networkDataSource = Mockito.mock(NetworkDataSource::class.java)
    private val apiKey : String = "b910f9e7-183e-4041-893c-76456b317c44"
    private val showFieldDetails : String = "main,body,headline,thumbnail"
    private lateinit var articleRepositoryImpl : ArticleRepositoryImpl

    private val fieldDetailArticle = FieldDetailsArticle("headline","main","body","thumbnail")
    private val contentArticle = ContentArticle("id","sectionName","webTitle","webPublicationDate", fieldDetailArticle)
    private val contentDataArticle = ContentDataArticle(contentArticle)
    private val responseDetailArticle = ResponseDetailArticle(contentDataArticle)
    private val detailArticle = DetailArticle("sectionName","webTitle",fieldDetailArticle.thumbnail,
        "webPublicationDate",fieldDetailArticle.headline,fieldDetailArticle.main,fieldDetailArticle.body)
    @Before
    fun initTest(){
        articleRepositoryImpl  = ArticleRepositoryImpl(networkDataSource)
    }
    @Test
    fun testGetDetailsArticle() {
        Mockito.`when`(networkDataSource.getDetailsArticle(apiKey, showFieldDetails,"id")).thenReturn(
            Observable.just(responseDetailArticle))

        var testObservable = TestObserver<DetailArticle>()
        var resultObservable = articleRepositoryImpl.getDetailsArticle(apiKey, showFieldDetails, "id")
        resultObservable.subscribe(testObservable)

        testObservable.assertComplete()
        testObservable.assertNoErrors()
        Assertions.assertThat(testObservable.values()).containsExactly(detailArticle)
        Mockito.verify(networkDataSource, VerificationModeFactory.times(1))
            .getDetailsArticle(apiKey, showFieldDetails, "id")
        Mockito.verifyNoMoreInteractions(networkDataSource)
    }
}