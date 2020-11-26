package com.example.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.storage.dao.MovieDao
import com.example.data.storage.models.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDB : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}