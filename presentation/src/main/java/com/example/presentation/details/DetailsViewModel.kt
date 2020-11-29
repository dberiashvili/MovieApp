package com.example.presentation.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.domain.models.MovieDomainModel
import com.example.domain.usecases.RemoveMovieFromFavoritesUseCase
import com.example.domain.usecases.SaveMovieToFavoritesUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailsViewModel @ViewModelInject constructor(
    private val saveMovieToFavoritesUseCase: SaveMovieToFavoritesUseCase,
    private val removeMovieFromFavoritesUseCase: RemoveMovieFromFavoritesUseCase
) : ViewModel() {
    fun addToFavorites(movie: MovieDomainModel) =
        saveMovieToFavoritesUseCase.saveMovie(movie).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun deleteFromFavorites(movie: MovieDomainModel) =
        removeMovieFromFavoritesUseCase.removeFavorite(movie).subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
}