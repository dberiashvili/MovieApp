package com.example.data.storage.dao

import androidx.room.*
import com.example.data.storage.models.MovieEntity
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface MovieDao {
    @Query("SELECT * FROM favourite_movies ORDER BY originalTitle")
    fun getSavedMovies(): Observable<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToFavourites(movie: MovieEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun storeMoviesToDatabase(movies: List<MovieEntity>): Completable

    @Delete
    fun deleteFromFavorites(movie: MovieEntity): Completable

    @Update
    fun updateMovie(movie: MovieEntity): Completable

    @Query("SELECT * FROM favourite_movies WHERE id = :id LIMIT 1")
    fun isFavorite(id: Int): Observable<MovieEntity>
}