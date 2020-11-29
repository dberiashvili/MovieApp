package com.example.domain.usecases

import com.example.domain.repository.Repository
import javax.inject.Inject

class GetMoviesFromDatabaseUseCase @Inject constructor(private val repository: Repository) {
    fun getMovies() = repository.getSavedMovies()
}