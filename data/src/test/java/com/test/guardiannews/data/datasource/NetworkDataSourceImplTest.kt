package com.test.guardiannews.data.datasource

import com.test.guardiannews.data.GuardianNetworkAPI
import com.test.guardiannews.data.model.articles.ResponseListNewsData
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class NetworkDataSourceImplTest{

    private val guardianNetworkAPI = Mockito.mock(GuardianNetworkAPI::class.java)

    private val apiKey : String = "b910f9e7-183e-4041-893c-76456b317c44"

    private val showField : String = "headline,thumbnail"
    private val pageSize  : Int = 50
    private val query  : String = "football"
    private lateinit var networkDataSourceImpl: NetworkDataSourceImpl

    @Before
    fun initTest(){
        networkDataSourceImpl = NetworkDataSourceImpl(guardianNetworkAPI)
    }
    @Test
    fun testGetAllArticles() {
        val listNewsData = Mockito.mock(ResponseListNewsData::class.java)
        Mockito.`when`(guardianNetworkAPI.getAllNews(apiKey, showField, pageSize, query)).thenReturn(
            Observable.just(listNewsData))

        var testObservable = TestObserver<ResponseListNewsData>()
        var resultObservable = networkDataSourceImpl.getAllArticles(apiKey, showField, pageSize, query)
        resultObservable.subscribe(testObservable)

        testObservable.assertComplete()
        testObservable.assertNoErrors()
        Assertions.assertThat(testObservable.values()).contains(listNewsData)
    }


}