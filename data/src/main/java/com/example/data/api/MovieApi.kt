package com.example.data.api

import retrofit2.http.GET

interface MovieApi {

    @GET("trending/movie/day")
    suspend fun getTrendingMovies()
}