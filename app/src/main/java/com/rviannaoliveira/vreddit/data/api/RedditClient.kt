package com.rviannaoliveira.vreddit.data.api

import com.rviannaoliveira.vreddit.AppApplication
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Criado por rodrigo on 18/10/17.
 */

class RedditClient {
    private val interceptor = HttpLoggingInterceptor()
    private val logging = interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    private val url = "https://www.reddit.com/r/Android/"

    private val httpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()


    fun <RedditService> createService(serviceClass: Class<RedditService>): RedditService {
        val builder = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())

        if (AppApplication.URL == null) {
            builder.baseUrl(url)
        } else {
            AppApplication.URL?.let {
                builder.baseUrl(it)
            }
        }
        val retrofit: Retrofit = builder.client(httpClient).build()
        return retrofit.create(serviceClass)
    }
}