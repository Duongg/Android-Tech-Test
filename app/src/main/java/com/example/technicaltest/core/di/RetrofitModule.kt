package com.example.technicaltest.core.di

import com.example.data.api.MovieApi
import com.example.data.api.interceptors.TokenHeaderInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {
    @Provides
    fun provideMovieApi(retrofit: Retrofit): MovieApi{
        return retrofit.create(MovieApi::class.java)
    }

    @Provides
    fun providesGson(): Gson {
        return GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
    }

    @Provides
    fun provideRestRetrofit(
        httpClient: OkHttpClient,
        gson: Gson
    ): Retrofit{
        return Retrofit.Builder()
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://api.themoviedb.org/3/")
            .build()
    }

    @Provides
    fun provideHttpRestClient(
        tokenHeaderInterceptor: TokenHeaderInterceptor
    ): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.addInterceptor {chain ->
            val url = chain
                .request()
                .url
                .newBuilder()
                .addQueryParameter("api_key", "47aa75b56464da7a186b813a50035cd4")
                .build()
            chain.proceed(chain.request().newBuilder().url(url).build())
        }
        httpClientBuilder.addInterceptor(tokenHeaderInterceptor)
        httpClientBuilder.readTimeout(0, TimeUnit.SECONDS)
        httpClientBuilder.writeTimeout(0, TimeUnit.SECONDS)
        val loggerInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        httpClientBuilder.addInterceptor(loggerInterceptor)
        return httpClientBuilder.build()
    }
}