package com.test.guardiannews.data.net

import android.content.Context
import com.test.guardiannews.data.GuardianNetworkAPI
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class GuardianNetworkAPITest {

    private val apiKey : String = "b910f9e7-183e-4041-893c-76456b317c44"

    private val showField : String = "headline,thumbnail"
    private val showFieldDetails : String = "main,body,headline,thumbnail"
    private val pageSize  : Int = 50
    private val query  : String = "football"
    private val idArticle : String= "politics/2020/oct/10/johnson-and-macron-hold-talks-on-coronavirus-and-brexit"
    private val context = Mockito.mock(Context::class.java)
    private val retrofit = RetrofitFactory.provideRetrofitClient(OkHttpClientFactoryTest().provideOkHttpClient(context))


    private val guardianNetworkAPI: GuardianNetworkAPI
        get() = retrofit.create(GuardianNetworkAPI::class.java)

    @Test
    fun getListArticles() {
        guardianNetworkAPI.getAllNews(apiKey, showField, pageSize, query).test().assertNoErrors()
    }

    @Test
    fun getDetailsArticles() {
        guardianNetworkAPI.getDetailsArticle(idArticle,apiKey,showFieldDetails).test().assertNoErrors()
    }
}