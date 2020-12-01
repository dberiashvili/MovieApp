package com.example.domain.usecases

import com.example.domain.repository.Repository
import javax.inject.Inject

class CheckFavoriteUseCase @Inject constructor(private val repository: Repository) {
    fun isFavorite(id: Int) = repository.isFavorite(id)
}