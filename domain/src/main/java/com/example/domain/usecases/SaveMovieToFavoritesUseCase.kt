package com.example.domain.usecases

import com.example.domain.models.MovieDomainModel
import com.example.domain.repository.Repository
import javax.inject.Inject

class SaveMovieToFavoritesUseCase @Inject constructor(private val repository: Repository) {
    fun saveMovie(movie: MovieDomainModel) = repository.saveMovieToDatabase(movie)
}
