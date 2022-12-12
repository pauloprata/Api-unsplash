package com.example.apiunsplash.Api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitService {

    private const val BASE_URL = "https://api.unsplash.com/"
    const val CLIENT_ID = "Ug8NHwzqRIUQAZE-R48KJ8sqpONXrXXBbV50sRCJ4Po"

    fun <T> getAPI( classe: Class<T> ) : T {
        return Retrofit.Builder()
            .baseUrl( BASE_URL )
            .addConverterFactory( GsonConverterFactory.create() )
            .client( getOkHTTP() )
            .build()
            .create( classe )
    }

    fun getOkHTTP() : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor( AuthInterceptor() )
            .build()
    }


}