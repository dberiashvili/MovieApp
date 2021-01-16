package com.example.presentation.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.domain.models.MovieDomainModel
import com.example.domain.usecases.CheckFavoriteUseCase
import com.example.domain.usecases.RemoveMovieFromFavoritesUseCase
import com.example.domain.usecases.SaveMovieToFavoritesUseCase
import com.example.domain.usecases.UpdateMovieUseCase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailsViewModel @ViewModelInject constructor(
    private val saveMovieToFavoritesUseCase: SaveMovieToFavoritesUseCase,
    private val removeMovieFromFavoritesUseCase: RemoveMovieFromFavoritesUseCase,
    private val checkFavoriteUseCase: CheckFavoriteUseCase,
    private val updateMovieUseCase: UpdateMovieUseCase
) : ViewModel() {
    fun addToFavorites(movie: MovieDomainModel) =
        saveMovieToFavoritesUseCase.saveMovie(movie).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun updateMovie(movie: MovieDomainModel) = updateMovieUseCase.updateMovie(movie)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun deleteFromFavorites(movie: MovieDomainModel) =
        removeMovieFromFavoritesUseCase.removeFavorite(movie).subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())

    fun checkFavorite(id: Int): Observable<MovieDomainModel> = checkFavoriteUseCase.isFavorite(id).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}