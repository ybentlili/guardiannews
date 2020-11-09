package com.test.guardiannews.data.net

import android.Manifest
import androidx.annotation.RequiresPermission
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val API_BASE_URL : String = "https://content.guardianapis.com"

object RetrofitFactory {
    @RequiresPermission(value = Manifest.permission.INTERNET)
    fun provideRetrofitClient(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
}