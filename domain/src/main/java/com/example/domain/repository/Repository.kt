package com.example.domain.repository

import com.example.domain.models.MovieDomainModel
import io.reactivex.Observable

interface Repository {
    fun fetchMoviesFromServer(page:Int): Observable<List<MovieDomainModel>>
}