package com.example.domain.repository

import com.example.domain.models.MovieDomainModel
import io.reactivex.Completable
import io.reactivex.Observable

interface Repository {
    fun fetchMoviesFromServer(page: Int): Observable<List<MovieDomainModel>>
    fun saveMovieToDatabase(movie: MovieDomainModel): Completable
    fun updateMovie(movie: MovieDomainModel): Completable
    fun saveMoviesToDatabase(movies:List<MovieDomainModel>): Completable
    fun removeMovieFromDatabase(movie: MovieDomainModel): Completable
    fun getSavedMovies(): Observable<List<MovieDomainModel>>
    fun isFavorite(id: Int): Observable<MovieDomainModel>
    fun searchMovie(query:String): Observable<List<MovieDomainModel>>
}