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

    @Delete
    fun deleteFromFavorites(movie: MovieEntity): Completable
}