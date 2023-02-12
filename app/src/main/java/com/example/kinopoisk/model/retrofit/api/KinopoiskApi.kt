package com.example.kinopoisk.model.retrofit.api

import com.example.kinopoisk.model.KinopoiskApp
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory

object KinopoiskApi {
    private val client = OkHttpClient.Builder().apply {
        addInterceptor(Interceptor { chain ->
            val builder = chain.request().newBuilder()
            builder.header("X-API-KEY", KinopoiskApp.API_KEY)
            builder.header("Content-Type", "application/json")
            return@Interceptor chain.proceed(builder.build())
        })
    }.build()

    private val retrofit by lazy {
        retrofit2.Retrofit.Builder().baseUrl(KinopoiskApp.API_URL).client(client).addConverterFactory(
            GsonConverterFactory.create()
        ).build()
    }
    val api: KinopoiskService by lazy { retrofit.create(KinopoiskService::class.java) }

}