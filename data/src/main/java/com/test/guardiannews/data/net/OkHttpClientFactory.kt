package com.test.guardiannews.data.net

import android.content.Context
import com.test.guardiannews.data.BuildConfig
import com.test.guardiannews.data.GuardianNetworkAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

open class OkHttpClientFactory {

   open fun provideInterceptLogging(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    open fun provideOkHttpClient(context: Context): OkHttpClient =
        OkHttpClient.Builder()
            .apply {
                if (BuildConfig.DEBUG) {
                    enableDebugTools()
                }
                updateTimeout()
            }
            .build()

    private fun OkHttpClient.Builder.enableDebugTools() {
        addInterceptor(provideInterceptLogging())
    }
    open fun provideGuardianAPIWs(retrofit: Retrofit): GuardianNetworkAPI =
        retrofit.create(GuardianNetworkAPI::class.java)

    private fun OkHttpClient.Builder.updateTimeout(read: Long = 60, write: Long = 60) {
        readTimeout(read, TimeUnit.SECONDS)
        writeTimeout(write, TimeUnit.SECONDS)
    }
}