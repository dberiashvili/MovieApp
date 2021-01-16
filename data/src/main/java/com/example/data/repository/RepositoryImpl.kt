package com.example.data.repository

import com.example.data.mappers.toDomainModel
import com.example.data.mappers.toEntity
import com.example.data.network.network_constants.Constants
import com.example.data.network.service.MovieService
import com.example.data.storage.MovieDB
import com.example.domain.models.MovieDomainModel
import com.example.domain.repository.Repository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val service: MovieService,
    private val database: MovieDB
) : Repository {
    override fun fetchMoviesFromServer(page: Int): Observable<List<MovieDomainModel>> {
        return service.getMovies(Constants.KEY, page).map {
            it.results.map {
                it.toDomainModel()
            }
        }
    }

    override fun saveMovieToDatabase(movie: MovieDomainModel): Completable {
        return database.movieDao().addToFavourites(movie.toEntity())
    }

    override fun saveMoviesToDatabase(movies: List<MovieDomainModel>): Completable {
        return database.movieDao().storeMoviesToDatabase(movies.map {
            it.toEntity()
        })
    }

    override fun removeMovieFromDatabase(movie: MovieDomainModel): Completable {
        return database.movieDao().deleteFromFavorites(movie.toEntity())
    }

    override fun getSavedMovies(): Observable<List<MovieDomainModel>> {
        return database.movieDao().getSavedMovies().map {
            it.map {
                it.toDomainModel()
            }
        }
    }

    override fun isFavorite(id: Int): Observable<MovieDomainModel> {
        return database.movieDao().isFavorite(id).map {
            it.toDomainModel()
        }
    }

    override fun searchMovie(query: String): Observable<List<MovieDomainModel>> {
        return service.searchMovie(Constants.KEY, query).map{
            it.results.map {
                it.toDomainModel()
            }
        }
    }

    override fun updateMovie(movie: MovieDomainModel): Completable {
        return database.movieDao().updateMovie(movie.toEntity())
    }
}