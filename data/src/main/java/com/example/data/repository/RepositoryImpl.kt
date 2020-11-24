package com.example.data.repository

import com.example.data.mappers.toDomainModel
import com.example.data.network.network_constants.Constants
import com.example.data.network.service.MovieService
import com.example.domain.models.MovieDomainModel
import com.example.domain.repository.Repository
import io.reactivex.Observable
import javax.inject.Inject

class RepositoryImpl@Inject constructor(private val service:MovieService):Repository {
    override fun fetchMoviesFromServer(page: Int): Observable<List<MovieDomainModel>> {
        return service.getMovies( Constants.KEY, page).map {
            it.results.map {
                it.toDomainModel()
            }
        }
    }

}