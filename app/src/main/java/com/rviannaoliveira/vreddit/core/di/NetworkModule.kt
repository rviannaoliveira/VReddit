package com.rviannaoliveira.vreddit.core.di

import com.rviannaoliveira.vreddit.AppApplication
import com.rviannaoliveira.vreddit.BuildConfig
import com.rviannaoliveira.vreddit.core.data.api.RedditService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Criado por rodrigo on 29/11/17.
 */
@Module
class NetworkModule(private val apiUrl: String) {

    @Provides
    @Singleton
    fun providesRedditRestApi(retrofitClient: Retrofit): RedditService = retrofitClient.create(RedditService::class.java)


    @Provides
    @Singleton
    fun providesRetrofitClient(okHttpClient: OkHttpClient): Retrofit {
        val builder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)

        if (AppApplication.URL == null) {
            builder.baseUrl(apiUrl)
        } else {
            AppApplication.URL?.let {
                builder.baseUrl(it)
            }
        }
        return builder.build()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(logging: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
    }

    @Provides
    @Singleton
    fun providesInterceptor(): Interceptor {
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logging.level = HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }

}