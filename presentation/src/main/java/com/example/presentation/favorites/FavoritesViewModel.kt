package com.example.presentation.favorites

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.domain.models.MovieDomainModel
import com.example.domain.usecases.GetMoviesFromDatabaseUseCase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FavoritesViewModel @ViewModelInject constructor(private val getMoviesFromDatabaseUseCase: GetMoviesFromDatabaseUseCase) :
    ViewModel() {
    fun getFavoriteMovies(): Observable<List<MovieDomainModel>> = getMoviesFromDatabaseUseCase.getMovies().subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}