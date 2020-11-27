package com.example.domain.repository

import com.example.domain.models.MovieDomainModel
import io.reactivex.Completable
import io.reactivex.Observable

interface Repository {
    fun fetchMoviesFromServer(page: Int): Observable<List<MovieDomainModel>>
    fun saveMovieToDatabase(movie: MovieDomainModel): Completable
    fun removeMovieFromDatabase(movie: MovieDomainModel): Completable
    fun getSavedMovies():Observable<List<MovieDomainModel>>
}