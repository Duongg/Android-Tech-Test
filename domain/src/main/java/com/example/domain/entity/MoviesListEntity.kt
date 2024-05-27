package com.example.domain.entity

data class MoviesListEntity(
    val results : List<MovieItemEntity>?
)

data class MovieItemEntity(
    val id: Int,
    val image: String?,
    val movieTitle: String?,
    val year: String?,
    val voteAverage: Double?,
)