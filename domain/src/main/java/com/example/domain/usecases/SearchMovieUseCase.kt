package com.example.domain.usecases

import com.example.domain.models.MovieDomainModel
import com.example.domain.repository.Repository
import io.reactivex.Observable
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(private val repository: Repository) {
    fun searchMovie(query:String): Observable<List<MovieDomainModel>> {
        return repository.searchMovie(query)
    }
}