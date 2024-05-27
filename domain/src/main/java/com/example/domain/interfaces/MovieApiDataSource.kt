package com.example.domain.interfaces

import com.example.domain.entity.MovieDetailEntity
import com.example.domain.entity.MovieItemEntity
import com.example.domain.entity.MoviesListEntity

interface MovieApiDataSource {
    suspend fun getMoviesTrendingList(page: Int): Result<MoviesListEntity>

    suspend fun getMovieListBySearchInput(queryText: String): Result<MoviesListEntity>

    suspend fun getMovieDetail(id: Int): Result<MovieDetailEntity>

    suspend fun saveMovieTrending(movieItemEntity: MovieItemEntity)

    suspend fun getAllMovieTrending(): List<MovieItemEntity>
   // suspend fun saveMovieDetail(movieDetailEntity: MovieDetailEntity)
}