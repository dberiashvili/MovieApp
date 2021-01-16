package com.example.domain.usecases

import com.example.domain.models.MovieDomainModel
import com.example.domain.repository.Repository
import javax.inject.Inject

class UpdateMovieUseCase @Inject constructor(private val repository: Repository) {
    fun updateMovie(movie:MovieDomainModel) = repository.updateMovie(movie)
}