package com.test.guardiannews.data

import com.test.guardiannews.data.model.article.ResponseDetailArticle
import io.reactivex.Observable
import com.test.guardiannews.data.model.articles.ResponseListNewsData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * This class describe all method used to connect between the app and the API
 *
 */
interface GuardianNetworkAPI {

    @GET("/search")
    fun getAllNews(@Query("api-key") apiKey:String, @Query("show-fields")showField :String,
                   @Query("page-size")pageSize :Int, @Query("q")query :String) : Observable<ResponseListNewsData>

    @GET("/{idArticle}")
    fun getDetailsArticle (@Path(value  ="idArticle", encoded = true) idArticle :String, @Query("api-key") apiKey:String, @Query("show-fields")showField :String) :
            Observable<ResponseDetailArticle>
}
