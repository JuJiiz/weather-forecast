package com.jujiiz.weatherforecast.core.di

import android.app.Application
import android.content.Context
import androidx.viewbinding.BuildConfig
import com.google.gson.GsonBuilder
import com.jujiiz.weatherforecast.core.BASE_SERVER_ENDPOINT
import com.jujiiz.weatherforecast.core.LOADING_TIMEOUT_IN_SECOND
import com.jujiiz.weatherforecast.core.WEATHER_API_KEY
import com.jujiiz.weatherforecast.core.network.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(
    includes = [
        RepositoryModule::class,
    ]
)
class AppModule {
    @Provides
    @Singleton
    fun provideApplicationContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .readTimeout(LOADING_TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
            .connectTimeout(LOADING_TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val original = chain.request()
                val originalHttpUrl = original.url
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("appid", WEATHER_API_KEY)
                    .build()
                original.newBuilder()
                    .url(url)
                    .build()
                    .let(chain::proceed)
            }
            .retryOnConnectionFailure(true)
            .hostnameVerifier { _, _ -> true }

        if (BuildConfig.DEBUG) client.addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })

        return client.build()
    }

    @Provides
    @Singleton
    fun provideApiService(client: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_SERVER_ENDPOINT)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().serializeNulls().create()
                )
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }
}