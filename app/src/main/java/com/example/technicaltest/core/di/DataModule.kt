package com.example.technicaltest.core.di

import android.content.Context
import com.example.data.datasource.MovieApiDataSourceImpl
import com.example.data.datasource.MovieDao
import com.example.domain.interfaces.MovieApiDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindMoviesDataSource(movieApiDataSource: MovieApiDataSourceImpl): MovieApiDataSource

    @Binds
    abstract fun bindAppContext(@ApplicationContext context: Context): Context


}