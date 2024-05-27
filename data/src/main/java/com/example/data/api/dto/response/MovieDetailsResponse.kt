package com.example.data.api.dto.response

import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(
    @SerializedName("backdrop_path") var backdropPath: String? = null,
    @SerializedName("budget") var budget: Int? = null,
    @SerializedName("genres") var genres: List<Genres> = arrayListOf(),
    @SerializedName("homepage") var homepage: String? = null,
    @SerializedName("id") var id: Int,
    @SerializedName("original_title") var originalTitle: String? = null,
    @SerializedName("overview") var overview: String? = null,
    @SerializedName("popularity") var popularity: Double? = null,
    @SerializedName("poster_path") var posterPath: String? = null,
    @SerializedName("production_companies") var productionCompanies: List<ProductionCompanies> = arrayListOf(),
    @SerializedName("production_countries") var productionCountries: List<ProductionCountries> = arrayListOf(),
    @SerializedName("release_date") var releaseDate: String? = null,
    @SerializedName("revenue") var revenue: Int? = null,
    @SerializedName("runtime") var runtime: Int? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("tagline") var tagline: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("vote_average") var voteAverage: Double? = null,
    @SerializedName("vote_count") var voteCount: Int? = null
)


data class Genres (
    @SerializedName("id"   ) var id   : Int?    = null,
    @SerializedName("name" ) var name : String? = null
)

data class ProductionCompanies (
    @SerializedName("id"             ) var id            : Int?    = null,
    @SerializedName("logo_path"      ) var logoPath      : String? = null,
    @SerializedName("name"           ) var name          : String? = null,
    @SerializedName("origin_country" ) var originCountry : String? = null
)

data class ProductionCountries (
    @SerializedName("iso_3166_1" ) var iso31661 : String? = null,
    @SerializedName("name"       ) var name     : String? = null
)