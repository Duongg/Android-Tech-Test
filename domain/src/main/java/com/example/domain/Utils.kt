package com.example.domain

import androidx.room.TypeConverter
import com.example.domain.entity.GenresEntity
import com.example.domain.entity.ProductCountryEntity
import com.example.domain.entity.ProductionCompanyEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable


class GenresConverter: Serializable {
    @TypeConverter
    fun fromGenresList(listGenresEntity: List<GenresEntity?>?): String? {
        if (listGenresEntity == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<GenresEntity?>?>() {}.getType()
        return gson.toJson(listGenresEntity, type)
    }

    @TypeConverter
    fun toGenresList(listGenresString: String?): List<GenresEntity?>? {
        if (listGenresString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<GenresEntity?>?>() {}.getType()
        return gson.fromJson(listGenresString, type)
    }
}

class ProductionCompanyConverter: Serializable {
    @TypeConverter
    fun fromProductionCompanyList(listProductionCompanyEntity: List<ProductionCompanyEntity?>?): String? {
        if (listProductionCompanyEntity == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<ProductionCompanyEntity?>?>() {}.getType()
        return gson.toJson(listProductionCompanyEntity, type)
    }

    @TypeConverter
    fun toProductionCompanyList(listProductionCompanyString: String?): List<ProductionCompanyEntity?>? {
        if (listProductionCompanyString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<ProductionCompanyEntity?>?>() {}.getType()
        return gson.fromJson(listProductionCompanyString, type)
    }
}


class ProductCountryConverter: Serializable {
    @TypeConverter
    fun fromProductCountryList(listProductCountry: List<ProductCountryEntity?>?): String? {
        if (listProductCountry == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<ProductCountryEntity?>?>() {}.getType()
        return gson.toJson(listProductCountry, type)
    }

    @TypeConverter
    fun toProductCountryList(listProductCountryString: String?): List<ProductCountryEntity?>? {
        if (listProductCountryString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<ProductCountryEntity?>?>() {}.getType()
        return gson.fromJson(listProductCountryString, type)
    }
}