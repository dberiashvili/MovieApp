package com.example.presentation.vm

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.domain.models.MovieDomainModel
import com.example.domain.usecases.RemoveMovieFromFavoritesUseCase
import com.example.domain.usecases.SaveMovieToFavoritesUseCase

class DetailsViewModel @ViewModelInject constructor(
    private val saveMovieToFavoritesUseCase: SaveMovieToFavoritesUseCase,
    private val removeMovieFromFavoritesUseCase: RemoveMovieFromFavoritesUseCase
) : ViewModel() {
    fun addToFavorites(movie: MovieDomainModel) = saveMovieToFavoritesUseCase.saveMovie(movie)
    fun deleteFromFavorites(movie: MovieDomainModel) =
        removeMovieFromFavoritesUseCase.removeFavorite(movie)
}