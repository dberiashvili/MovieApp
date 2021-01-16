package com.example.domain.usecases

import com.example.domain.models.MovieDomainModel
import com.example.domain.repository.Repository
import javax.inject.Inject

class SaveMoviesToDatabaseUseCase @Inject constructor(private val repository: Repository) {
    fun saveMoviesToDataBase(movies:List<MovieDomainModel>) = repository.saveMoviesToDatabase(movies)
}