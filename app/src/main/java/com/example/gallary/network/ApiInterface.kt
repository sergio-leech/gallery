package com.example.gallary.network

import com.example.gallary.model.Photo
import com.example.gallary.model.SearchPhoto
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {

    @Headers(ACCESS_KEY)
    @GET("photos")
    fun getPhotosAsync(
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int
    ): Deferred<List<Photo>>

    @Headers(ACCESS_KEY)
    @GET("search/photos")
    fun searchPhotosAsync(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int
    ): Deferred<SearchPhoto>
}