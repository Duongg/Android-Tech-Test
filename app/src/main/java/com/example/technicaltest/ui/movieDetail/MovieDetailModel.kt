package com.example.technicaltest.ui.movieDetail

import com.example.data.api.dto.response.Genres
import com.example.data.api.dto.response.MovieDetailsResponse
import com.example.data.api.dto.response.ProductionCompanies
import com.example.data.api.dto.response.ProductionCountries
import com.example.data.api.dto.response.SpokenLanguages
import com.example.domain.entity.GenresEntity
import com.example.domain.entity.MovieDetailEntity
import com.example.domain.entity.ProductCountryEntity
import com.example.domain.entity.ProductionCompanyEntity
import com.example.domain.entity.SpokenLanguageEntity

data class MovieDetailModel(
    val id: Int,
    val adult: Boolean?,
    val backdropPath: String?,
    val belongsToCollection: String?,
    val budget: Int?,
    val genres: List<GenresModel>?,
    val homePage: String?,
    val imdbId: String?,
    val originCountry: List<String>,
    val originLanguage: String?,
    val originTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val productionCompanies: List<ProductionCompanyModel>,
    val productCountries: List<ProductCountryModel>,
    val releaseDate: String?,
    val revenue: Int?,
    val runTime: Int?,
    val spokenLanguages: List<SpokenLanguageModel>,
    val status: String?,
    val tagLine: String?,
    val title: String?,
    val video: Boolean?,
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

data class SpokenLanguageModel(
    val englishName: String?,
    val iso6391: String?,
    val name: String?,
)


fun MovieDetailEntity.toModel() = MovieDetailModel(
    id = id,
    adult = adult,
    backdropPath = backdropPath,
    belongsToCollection = belongsToCollection,
    budget = budget,
    genres = genres?.map { it.toModel() },
    homePage = homePage,
    imdbId = imdbId,
    originCountry = originCountry,
    originLanguage = originLanguage,
    originTitle = originTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    productionCompanies = productionCompanies.map { it.toModel() },
    productCountries = productCountries.map { it.toModel() },
    releaseDate = releaseDate,
    revenue = revenue,
    runTime = runTime,
    spokenLanguages = spokenLanguages.map { it.toModel() },
    status = status,
    tagLine = tagLine,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount,
)

fun GenresEntity.toModel() = GenresModel(
    id = id,
    name = name,
)

fun ProductionCompanyEntity.toModel() = ProductionCompanyModel(
    id = id,
    logoPath = logoPath,
    name = name,
    originCountry = originCountry,
)

fun ProductCountryEntity.toModel() = ProductCountryModel(
    iso31661 = iso31661,
    name = name,
)

fun SpokenLanguageEntity.toModel() = SpokenLanguageModel(
    englishName = englishName,
    iso6391 = iso6391,
    name = name,
)