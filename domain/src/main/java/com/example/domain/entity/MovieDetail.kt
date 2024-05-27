package com.example.domain.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation


@Entity(tableName = "movie_details_tb")
data class MovieDetailTb(
    @Embedded
    val movieDetail: MovieDetail,
    @Relation(
        parentColumn = "idGenres",
        entityColumn = "id"
    )
    val genres: List<Genres>?,

    @Relation(
        parentColumn = "idProductionCompany",
        entityColumn = "id",

        )
    val productionCompanies: List<ProductionCompany>,
    @Relation(
        parentColumn = "idProductCountry",
        entityColumn = "id"
    )
    val productCountries: List<ProductCountry>,

)

@Entity(tableName = "movie_details")
data class MovieDetail(
    @PrimaryKey val id: Int,
    val backdropPath: String?,
    val budget: Int?,
    val homePage: String?,
    val originTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val releaseDate: String?,
    val revenue: Int?,
    val runTime: Int?,
    val status: String?,
    val tagLine: String?,
    val title: String?,
    val voteAverage: Double?,
    val voteCount: Int?,
)

@Entity(tableName = "movie_genres")
data class Genres(
    @PrimaryKey val idGenres: Int?,
    val id: Int,
    val name: String?,
)

@Entity(tableName = "movie_productionCompany")
data class ProductionCompany(
    @PrimaryKey val idProductionCompany: Int?,
    val id: Int,
    val logoPath: String?,
    val name: String?,
    val originCountry: String?,
)

@Entity(tableName = "movie_productCountry")
data class ProductCountry(
    @PrimaryKey val idProductCountry: Int,
    val id: Int,
    val iso31661: String?,
    val name: String?,
)