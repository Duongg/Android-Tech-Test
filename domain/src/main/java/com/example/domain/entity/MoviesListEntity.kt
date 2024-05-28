package com.example.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

data class MoviesListEntity(
    val results : List<MovieItemEntity>?
)

@Entity(tableName = "movie_trending")
data class MovieItemEntity(
    @PrimaryKey val id: Int,
    val image: String?,
    val movieTitle: String?,
    val year: String?,
    val voteAverage: Double?,
)