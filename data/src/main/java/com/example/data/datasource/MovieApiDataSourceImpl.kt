package com.example.data.datasource

import com.example.data.api.MovieApi
import com.example.data.api.mapper.toEntity
import com.example.domain.entity.MovieDetailEntity
import com.example.domain.entity.MoviesListEntity
import com.example.domain.interfaces.MovieApiDataSource
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieApiDataSourceImpl @Inject constructor(
    private val movieApi: MovieApi,
    dispatcher: CoroutineDispatcher,
) : RetrofitDataSource(coroutineDispatcher = dispatcher), MovieApiDataSource{
    override suspend fun getMoviesTrendingList(): Result<MoviesListEntity> {
        return apiCall {
            movieApi.getTrendingMovies()
        }.map { it.toEntity() }
    }

    override suspend fun getMovieListBySearchInput(queryText: String): Result<MoviesListEntity> {
        return apiCall {
            movieApi.getMoviesList(queryText)
        }.map { it.toEntity() }
    }

    override suspend fun getMovieDetail(id: Int): Result<MovieDetailEntity> {
        return apiCall {
            movieApi.getMoviesDetails(id)
        }.map { it.toEntity() }
    }
}