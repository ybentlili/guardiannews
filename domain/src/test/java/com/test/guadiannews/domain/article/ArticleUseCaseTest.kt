package com.test.guadiannews.domain.article

import com.test.guadiannews.domain.model.DetailArticle
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import junit.framework.TestCase
import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.internal.verification.VerificationModeFactory
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ArticleUseCaseTest : TestCase() {

    private val articleRepository = Mockito.mock(ArticleRepository::class.java)
    private val articleUseCase = ArticleUseCase(articleRepository)
    private val apiKey : String = "b910f9e7-183e-4041-893c-76456b317c44"
    private val idArticle : String = "idArticle"
    private val showFiledDetails : String = "main,body,headline,thumbnail"

    private val detailArticle = DetailArticle(
        sectionName = "Test",
        title = "Title",
        urlImage = "https://image.jpg",
        publishDate = "2020-10-10T16:10:56Z",
        filedHeadline = "headline",
        filedMain = "main",
        filedBody = "body"
    )
    @Test
    fun testGetDetailsArticle() {

        Mockito.`when`(articleRepository.getDetailsArticle(apiKey,showFiledDetails, idArticle)).thenReturn(
            Observable.just(detailArticle))

        val testArticles = TestObserver<DetailArticle>()

        val observable = articleUseCase.getDetailsArticle(apiKey, showFiledDetails,idArticle)
        observable.subscribe(testArticles)

        testArticles.assertComplete()
        testArticles.assertNoErrors()
        Assertions.assertThat(testArticles.values()).contains(detailArticle)
        Mockito.verify(articleRepository, VerificationModeFactory.times(1))
            .getDetailsArticle(apiKey, showFiledDetails, idArticle)
        Mockito.verifyNoMoreInteractions(articleRepository)
    }
}