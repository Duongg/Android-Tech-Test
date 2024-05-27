package com.example.domain.entity

data class MovieDetailEntity(
    val id: Int,
    val adult: Boolean?,
    val backdropPath: String?,
    val belongsToCollection: String?,
    val budget: Int?,
    val genres: List<GenresEntity>?,
    val homePage: String?,
    val imdbId: String?,
    val originCountry: List<String>,
    val originLanguage: String?,
    val originTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val productionCompanies: List<ProductionCompanyEntity>,
    val productCountries: List<ProductCountryEntity>,
    val releaseDate: String?,
    val revenue: Int?,
    val runTime: Int?,
    val spokenLanguages: List<SpokenLanguageEntity>,
    val status: String?,
    val tagLine: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?,
)

data class GenresEntity(
    val id: Int?,
    val name: String?,
)

data class ProductionCompanyEntity(
    val id: Int?,
    val logoPath: String?,
    val name: String?,
    val originCountry: String?,
)

data class ProductCountryEntity(
    val iso31661: String?,
    val name: String?,
)

data class SpokenLanguageEntity(
    val englishName: String?,
    val iso6391: String?,
    val name: String?,
)