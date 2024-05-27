package com.example.domain.interfaces

import com.example.domain.entity.MovieDetailEntity
import com.example.domain.entity.MoviesListEntity

interface MovieApiDataSource {
    suspend fun getMoviesTrendingList(): Result<MoviesListEntity>

    suspend fun getMovieListBySearchInput(queryText: String): Result<MoviesListEntity>

    suspend fun getMovieDetail(id: Int): Result<MovieDetailEntity>
}