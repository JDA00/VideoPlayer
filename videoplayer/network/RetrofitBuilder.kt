package com.jda00.videoplayer.network

import com.jda00.videoplayer.util.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
Retrofit Builder object with config.
 */


object RetrofitBuilder {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
    val service: RetrofitService by lazy {
        retrofit.build().create(RetrofitService::class.java)
    }
}