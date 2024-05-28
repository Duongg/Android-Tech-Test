package com.example.data.api.mapper

import com.example.data.api.dto.response.Genres
import com.example.data.api.dto.response.MovieDetailsResponse
import com.example.data.api.dto.response.MovieItemResponse
import com.example.data.api.dto.response.MovieListResponse
import com.example.data.api.dto.response.ProductionCompanies
import com.example.data.api.dto.response.ProductionCountries
import com.example.domain.entity.GenresEntity
import com.example.domain.entity.MovieDetailEntity
import com.example.domain.entity.MovieItemEntity
import com.example.domain.entity.MoviesListEntity
import com.example.domain.entity.ProductCountryEntity
import com.example.domain.entity.ProductionCompanyEntity

fun MovieListResponse.toEntity() = MoviesListEntity(
    results = results.map { it.toEntity() }
)

fun MovieItemResponse.toEntity() = MovieItemEntity(
    id = id,
    image = posterPath,
    movieTitle = originalTitle,
    year = releaseDate,
    voteAverage = voteAverage,
)

fun MovieDetailsResponse.toEntity() = MovieDetailEntity(
    id = id,
    backdropPath = backdropPath,
    budget = budget,
    genres = genres.map { it.toEntity() },
    homePage = homepage,
    originTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    productionCompanies = productionCompanies.map { it.toEntity() },
    productCountries = productionCountries.map { it.toEntity() },
    releaseDate = releaseDate,
    revenue = revenue,
    runTime = runtime,
    status = status,
    tagLine = tagline,
    title = title,
    voteAverage = voteAverage,
    voteCount = voteCount,
)

fun Genres.toEntity() = GenresEntity(
    idGenres = id,
    name = name,
)

fun ProductionCompanies.toEntity() = ProductionCompanyEntity(
    idProductionCompany = id,
    logoPath = logoPath,
    name = name,
    originCountry = originCountry,
)

fun ProductionCountries.toEntity() = ProductCountryEntity(
    idProductCountry = 0,
    iso31661 = iso31661,
    name = name,
)