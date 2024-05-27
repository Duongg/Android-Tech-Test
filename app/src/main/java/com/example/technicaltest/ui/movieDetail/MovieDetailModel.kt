package com.example.technicaltest.ui.movieDetail

import com.example.domain.entity.GenresEntity
import com.example.domain.entity.MovieDetailEntity
import com.example.domain.entity.ProductCountryEntity
import com.example.domain.entity.ProductionCompanyEntity

data class MovieDetailModel(
    val id: Int,
    val backdropPath: String?,
    val budget: Int?,
    val genres: List<GenresModel>?,
    val homePage: String?,
    val originTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val productionCompanies: List<ProductionCompanyModel>,
    val productCountries: List<ProductCountryModel>,
    val releaseDate: String?,
    val revenue: Int?,
    val runTime: Int?,
    val status: String?,
    val tagLine: String?,
    val title: String?,
    val voteAverage: Double?,
    val voteCount: Int?,
)

data class GenresModel(
    val id: Int?,
    val name: String?,
)

data class ProductionCompanyModel(
    val id: Int?,
    val logoPath: String?,
    val name: String?,
    val originCountry: String?,
)

data class ProductCountryModel(
    val iso31661: String?,
    val name: String?,
)


fun MovieDetailEntity.toModel() = MovieDetailModel(
    id = id,
    backdropPath = backdropPath,
    budget = budget,
    genres = genres?.map { it.toModel() },
    homePage = homePage,
    originTitle = originTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    productionCompanies = productionCompanies.map { it.toModel() },
    productCountries = productCountries.map { it.toModel() },
    releaseDate = releaseDate,
    revenue = revenue,
    runTime = runTime,
    status = status,
    tagLine = tagLine,
    title = title,
    voteAverage = voteAverage,
    voteCount = voteCount,
)

fun GenresEntity.toModel() = GenresModel(
    id = idGenres,
    name = name,
)

fun ProductionCompanyEntity.toModel() = ProductionCompanyModel(
    id = idProductionCompany,
    logoPath = logoPath,
    name = name,
    originCountry = originCountry,
)

fun ProductCountryEntity.toModel() = ProductCountryModel(
    iso31661 = iso31661,
    name = name,
)