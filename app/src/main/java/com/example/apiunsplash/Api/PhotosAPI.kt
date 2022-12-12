package com.example.apiunsplash.Api

import com.example.apiunsplash.model.PhotosResponse
import com.example.apiunsplash.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosAPI {

    @GET("/photos")
    suspend fun  recuperarPhotos(
    ): Response<PhotosResponse>

    @GET("search/photos")
    suspend fun  pesquisarPhotos(
        @Query("query") resposta: String
    ): Response<SearchResponse>
}