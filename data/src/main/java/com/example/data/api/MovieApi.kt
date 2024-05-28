package com.example.data.api

import com.example.data.api.dto.response.MovieDetailsResponse
import com.example.data.api.dto.response.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("trending/movie/day")
    suspend fun getTrendingMovies(@Query("page") page: Int) : Response<MovieListResponse>

    @GET("search/movie")
    suspend fun getMoviesList(@Query("query") queryText: String): Response<MovieListResponse>

    @GET("movie/{movie_id}")
    suspend fun getMoviesDetails(@Path("movie_id") movieId: Int): Response<MovieDetailsResponse>
}