package com.jda00.videoplayer

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jda00.videoplayer.data.MainRepository
import com.jda00.videoplayer.data.Movie
import com.jda00.videoplayer.network.RetrofitBuilder
import kotlinx.coroutines.launch

/**
ViewModel config.
 */


class MainViewModel : ViewModel() {
    private val apiService = RetrofitBuilder.service
    private lateinit var repository: MainRepository
    var movies: List<Movie> by mutableStateOf(listOf())


    // Initialize
    init {
        fetchMovies()
    }

    // Add Movie objects from repository to ViewModel
    fun fetchMovies() {

        repository = MainRepository(apiService)
        viewModelScope.launch {
            var response = repository.fetchMovies()
            when (response) {
                is MainRepository.Result.Success -> {

                    Log.d("MainViewModel", "SUCCESS")
                    movies = response.movieList
                }
                is MainRepository.Result.Failure -> {
                    Log.d("MainViewModel", "FAILURE")
                }
            }
        }
    }
}