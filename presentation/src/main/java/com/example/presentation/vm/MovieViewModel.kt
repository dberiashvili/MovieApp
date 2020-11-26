package com.example.presentation.vm

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.domain.models.MovieDomainModel
import com.example.domain.usecases.FetchResponseFromServerUseCase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieViewModel @ViewModelInject constructor(
    private val fetchMoviesFromServerUseCase: FetchResponseFromServerUseCase
) :
    ViewModel() {

    fun fetchMoviesFromServer(page: Int): Observable<List<MovieDomainModel>> =
        fetchMoviesFromServerUseCase.fetchMovies(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


}
