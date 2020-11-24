package com.example.movieapp.di

import com.example.data.network.network_constants.Constants
import com.example.data.network.service.MovieService
import com.example.domain.repository.Repository
import com.example.domain.usecases.FetchResponseFromServerUseCase
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
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
        .addConverterFactory(Json{
            ignoreUnknownKeys = true
        }.asConverterFactory("application/json".toMediaType()))
        .build()


    @Provides
    fun provideService(retrofit: Retrofit): MovieService = retrofit.create(MovieService::class.java)

    @Provides
    fun provideFetchResponseFromServerUseCase(repository: Repository): FetchResponseFromServerUseCase =
        FetchResponseFromServerUseCase(repository)
}