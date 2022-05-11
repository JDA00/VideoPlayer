package com.jda00.videoplayer.data

import android.util.Log
import com.jda00.videoplayer.network.RetrofitService

/**
Retrieves Json objects from Retrofit
 */


class MainRepository(private val retrofitService: RetrofitService) {

    sealed class Result {
        object LOADING : Result()
        data class Success(val movieList: List<Movie>) : Result()
        data class Failure(val throwable: Throwable) : Result()
    }


    suspend fun fetchMovies(): Result {
        return try {
            val movieList = retrofitService.getMovieList()
            Log.d("MOVIE RETRIEVAL", "SUCCESS " + movieList.size)
            Result.Success(movieList = movieList)
        } catch (exception: Exception) {
            Log.d("MOVIE RETRIEVAL", "FAILURE ")

            Result.Failure(exception)
        }
    }
}