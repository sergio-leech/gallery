package com.example.gallary.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val ACCESS_KEY = "Authorization: Client-ID hLlGX0VjDVsig50VHGu7b9Is2hWUhesL2lTOWser_ts"
private const val BASE_URL = "https://api.unsplash.com/"

private fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    val httpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
    return httpClient.build()
}

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .client(createOkHttpClient())
    .build()

object UnsplashApi {
    val retrofitService: ApiInterface by lazy { retrofit.create(ApiInterface::class.java) }
}