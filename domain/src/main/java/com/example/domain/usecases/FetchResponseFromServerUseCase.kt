package com.example.domain.usecases

import com.example.domain.repository.Repository
import javax.inject.Inject


class FetchResponseFromServerUseCase @Inject constructor(private val repository: Repository) {
    fun fetchMovies(page: Int) = repository.fetchMoviesFromServer(page)
}