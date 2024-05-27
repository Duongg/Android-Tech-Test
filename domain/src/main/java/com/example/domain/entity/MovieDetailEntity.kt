package com.example.domain.entity

data class MovieDetailEntity(
    val id: Int,
    val backdropPath: String?,
    val budget: Int?,
    val genres: List<GenresEntity>?,
    val homePage: String?,
    val originTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val productionCompanies: List<ProductionCompanyEntity>,
    val productCountries: List<ProductCountryEntity>,
    val releaseDate: String?,
    val revenue: Int?,
    val runTime: Int?,
    val status: String?,
    val tagLine: String?,
    val title: String?,
    val voteAverage: Double?,
    val voteCount: Int?,
)

data class GenresEntity(
    val idGenres: Int?,
    val name: String?,
)

data class ProductionCompanyEntity(
    val idProductionCompany: Int?,
    val logoPath: String?,
    val name: String?,
    val originCountry: String?,
)

data class ProductCountryEntity(
    val idProductCountry: Int,
    val iso31661: String?,
    val name: String?,
)