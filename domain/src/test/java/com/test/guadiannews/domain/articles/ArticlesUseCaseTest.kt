package com.test.guadiannews.domain.articles

import com.test.guadiannews.domain.model.Article
import com.test.guadiannews.domain.model.ListArticle
import com.test.guadiannews.domain.utils.TestException
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.assertj.core.api.Assertions
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.internal.verification.VerificationModeFactory.times
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ArticlesUseCaseTest {

    //Valid Params
    private val apiKey : String = "b910f9e7-183e-4041-893c-76456b317c44"
    private val showField :String = "headline,thumbnail"
    private val query  : String = "football"
    private val pageSize  : Int = 50

    private val articlesRepository = Mockito.mock(ArticlesRepository::class.java)
    private val articlesUseCase = ArticlesUseCase(articlesRepository)

    private val firstArticle = Article(
        "id Article",
        "Section Name",
        "Title",
        "https://image.jpg",
        "2020-10-10T16:10:56Z"
    )

    private val list = listOf(
        firstArticle.copy("id Article"), firstArticle.copy("id Article 2"), firstArticle.copy(
            "id Article 3"
        )
    )
    private val listArticle = ListArticle(100, 50, list)

    @Test
    fun apiKeyTest() {
        Assert.assertEquals(apiKey.isNotEmpty(),true)
    }
    @Test
    fun successGetAllArticlesTest() {

        Mockito.`when`(articlesRepository.getListArticle(apiKey, showField, pageSize, query)).thenReturn(
            Observable.just(listArticle)
        )
        val testArticles = TestObserver<ListArticle>()

        val observable = articlesUseCase.getAllArticles(apiKey, showField, pageSize, query)
        observable.subscribe(testArticles)

        testArticles.assertComplete()
        testArticles.assertNoErrors()
        Assertions.assertThat(testArticles.values()).contains(listArticle)
        verify(articlesRepository, times(1)).getListArticle(apiKey, showField, pageSize, query)
        Mockito.verifyNoMoreInteractions(articlesRepository)

    }

    @Test
    fun errorGetAllArticlesTest() {
        var testException = TestException()
        Mockito.`when`(articlesRepository.getListArticle(apiKey, showField, pageSize, query)).thenReturn(
            Observable.error(testException)
        )

        val observable = articlesUseCase.getAllArticles(apiKey, showField, pageSize, query)
        val testArticles = TestObserver<ListArticle>()

        observable.subscribe(testArticles)

        verify(articlesRepository).getListArticle(apiKey, showField, pageSize, query)
        testArticles.assertError(testException)

    }
}