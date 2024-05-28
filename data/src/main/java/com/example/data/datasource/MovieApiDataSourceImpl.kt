package com.example.data.datasource

import com.example.data.api.MovieApi
import com.example.data.api.mapper.toEntity
import com.example.domain.entity.MovieDetailEntity
import com.example.domain.entity.MovieItemEntity
import com.example.domain.entity.MoviesListEntity
import com.example.domain.interfaces.MovieApiDataSource
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieApiDataSourceImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val dao: MovieDao,
    dispatcher: CoroutineDispatcher,
) : RetrofitDataSource(coroutineDispatcher = dispatcher), MovieApiDataSource{
    override suspend fun getMoviesTrendingList(page: Int): Result<MoviesListEntity> {
        return apiCall {
            movieApi.getTrendingMovies(page = page)
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

    override suspend fun saveMovieTrending(movieItemEntity: MovieItemEntity) {
        dao.insertMovieTrending(movieItemEntity)
    }

    override suspend fun getAllMovieTrending(): List<MovieItemEntity> {
        return dao.getMovieTrending()
    }


    override suspend fun saveMovieDetail(movieDetailEntity: MovieDetailEntity) {
        dao.insertMovieDetail(movieDetailEntity)
    }

    override suspend fun getMovieDetailFromDB(id: Int): MovieDetailEntity {
        return dao.getMovieDetail(id)
    }
}