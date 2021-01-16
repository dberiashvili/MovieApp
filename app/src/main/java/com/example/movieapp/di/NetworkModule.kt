package com.example.movieapp.di

import com.example.data.network.network_constants.Constants
import com.example.data.network.service.MovieService
import com.example.domain.repository.Repository
import com.example.domain.usecases.FetchResponseFromServerUseCase
import com.example.domain.usecases.SearchMovieUseCase
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {
    @ExperimentalSerializationApi
    @Provides
    fun provideNetwork(): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }.asConverterFactory("application/json".toMediaType()))
        .client(provideHTTPClient())
        .build()


    @Provides
    fun provideService(retrofit: Retrofit): MovieService = retrofit.create(MovieService::class.java)

    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return loggingInterceptor
    }

    @Provides
    fun provideHTTPClient() = OkHttpClient.Builder()
        .addInterceptor(provideInterceptor())
        .build()

    @Provides
    fun provideFetchResponseFromServerUseCase(repository: Repository): FetchResponseFromServerUseCase =
        FetchResponseFromServerUseCase(repository)

    @Provides
    fun searchMovieUseCase(repository: Repository): SearchMovieUseCase =
        SearchMovieUseCase(repository)
}