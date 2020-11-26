package com.example.movieapp.di

import android.content.Context
import androidx.room.Room
import com.example.data.storage.MovieDB
import com.example.data.storage.dao.MovieDao
import com.example.domain.repository.Repository
import com.example.domain.usecases.RemoveMovieFromFavoritesUseCase
import com.example.domain.usecases.SaveMovieToFavoritesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {
    @Provides
    fun provideMovieDB(@ApplicationContext context: Context): MovieDB {
        return Room
            .databaseBuilder(
                context,
                MovieDB::class.java,
                "movie_db"
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideMovieDao(movieDB: MovieDB): MovieDao = movieDB.movieDao()

    @Provides
    fun provideSaveMovieToFavoritesUseCase(repository: Repository): SaveMovieToFavoritesUseCase =
        SaveMovieToFavoritesUseCase(repository)

    @Provides
    fun provideRemoveMovieFromFavoritesUseCase(repository: Repository): RemoveMovieFromFavoritesUseCase =
        RemoveMovieFromFavoritesUseCase(repository)
}