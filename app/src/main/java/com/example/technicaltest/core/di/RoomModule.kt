package com.example.technicaltest.core.di

import android.app.Application
import androidx.room.Room
import com.example.data.datasource.MovieDao
import com.example.data.datasource.MovieDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    @Provides
    @Singleton
    fun provideMovieDatabase(app: Application): MovieDataBase {
        return Room.databaseBuilder(
            app,
            MovieDataBase::class.java,
            MovieDataBase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(movieDataBase: MovieDataBase): MovieDao {
        return movieDataBase.movieDao()
    }
}