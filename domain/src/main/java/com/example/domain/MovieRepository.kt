package com.example.domain

import com.example.domain.entity.MovieDetailEntity
import com.example.domain.entity.MoviesListEntity
import com.example.domain.interfaces.MovieApiDataSource
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieApiDataSource: MovieApiDataSource,
    dispatcher: CoroutineDispatcher
) : CoroutineAware(dispatcher) {
    suspend fun getTrendingMoviesList(): Result<MoviesListEntity> = async {
        movieApiDataSource.getMoviesTrendingList()
    }

    suspend fun getMovieListBySearchInput(queryText: String): Result<MoviesListEntity> = async {
        movieApiDataSource.getMovieListBySearchInput(queryText)
    }

    suspend fun getMovieDetails(id: Int): Result<MovieDetailEntity> = async {
        movieApiDataSource.getMovieDetail(id)
    }
}