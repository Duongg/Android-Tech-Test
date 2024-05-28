package com.example.technicaltest.ui.movieList

import com.example.domain.entity.MovieItemEntity

data class MovieItemModel(
    val id: Int,
    val image: String,
    val movieTitle: String,
    val year: String,
    val voteAverage: Double,
)

fun MovieItemEntity.toModel() = MovieItemModel(
    id = id,
    image = image ?: "",
    movieTitle = movieTitle ?: "",
    year = year ?: "",
    voteAverage = voteAverage ?: 0.0
)