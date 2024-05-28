package com.example.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.domain.GenresConverter
import com.example.domain.ProductCountryConverter
import com.example.domain.ProductionCompanyConverter
import com.example.domain.entity.MovieDetailEntity
import com.example.domain.entity.MovieItemEntity

@Database(
    entities = [MovieItemEntity::class, MovieDetailEntity::class],
    version = 1
)
@TypeConverters(GenresConverter::class, ProductCountryConverter::class, ProductionCompanyConverter::class)
abstract class MovieDataBase: RoomDatabase(){
    abstract fun movieDao(): MovieDao

    companion object {
        const val DATABASE_NAME = "movies_db"
    }
}