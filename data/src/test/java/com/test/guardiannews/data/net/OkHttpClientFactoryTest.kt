package com.test.guardiannews.data.net

import android.content.Context
import okhttp3.OkHttpClient


class OkHttpClientFactoryTest : OkHttpClientFactory() {

    override fun provideOkHttpClient(context: Context): OkHttpClient = OkHttpClient.Builder().build()
}
