package com.jda00.videoplayer.network

import com.jda00.videoplayer.data.Movie
import retrofit2.http.GET

/**
Retrofit Interface with GET path
 */


interface RetrofitService {

    @GET("videos")
    suspend fun getMovieList(): List<Movie>

}