package com.example.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.domain.entity.MovieItemEntity

@Database(
    entities = [MovieItemEntity::class],
    version = 1
)
abstract class MovieDataBase: RoomDatabase(){
    abstract fun movieDao(): MovieDao

    companion object {
        const val DATABASE_NAME = "movies_db"
    }
}