package com.example.presentation.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.domain.models.MovieDomainModel
import com.example.domain.usecases.FetchResponseFromServerUseCase
import com.example.domain.usecases.SaveMoviesToDatabaseUseCase
import com.example.domain.usecases.SearchMovieUseCase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel @ViewModelInject constructor(
    private val fetchMoviesFromServerUseCase: FetchResponseFromServerUseCase,
    private val saveMoviesToDatabaseUseCase: SaveMoviesToDatabaseUseCase,
    private val searchMovieUseCase: SearchMovieUseCase
) :
    ViewModel() {

    fun fetchMoviesFromServer(page: Int): Observable<List<MovieDomainModel>> =
        fetchMoviesFromServerUseCase.fetchMovies(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun saveMoviesToDatabase(movies: List<MovieDomainModel>) =
        saveMoviesToDatabaseUseCase.saveMoviesToDataBase(movies)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun searchMovie(query: String) =
        searchMovieUseCase.searchMovie(query).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

