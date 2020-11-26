package com.example.domain.usecases

import com.example.domain.models.MovieDomainModel
import com.example.domain.repository.Repository
import javax.inject.Inject

class RemoveMovieFromFavoritesUseCase @Inject constructor(private val repository: Repository) {
    fun removeFavorite(movie: MovieDomainModel) = repository.removeMovieFromDatabase(movie)
}