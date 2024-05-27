package com.example.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.entity.MovieDetailEntity
import com.example.domain.entity.MovieItemEntity

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieTrending(movieItemEntity: MovieItemEntity)

    @Query("SELECT * FROM movie_trending")
    fun getMovieTrending() : List<MovieItemEntity>

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertMovieDetail(movieDetailEntity: MovieDetailEntity)
}