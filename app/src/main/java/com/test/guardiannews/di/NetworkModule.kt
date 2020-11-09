package com.test.guardiannews.di

import com.test.guardiannews.data.datasource.NetworkDataSource
import com.test.guardiannews.data.datasource.NetworkDataSourceImpl
import com.test.guardiannews.data.net.OkHttpClientFactory
import com.test.guardiannews.data.net.RetrofitFactory.provideRetrofitClient
import org.koin.dsl.module


val networkModule = module {
    single { OkHttpClientFactory().provideOkHttpClient(get()) }
    single { provideRetrofitClient(get()) }
    single { OkHttpClientFactory().provideGuardianAPIWs(get()) }
    single { NetworkDataSourceImpl(guardianNetworkAPI = get()) as NetworkDataSource }
}

