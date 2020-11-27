package com.example.data.mappers

import com.example.data.network.dto.MovieDTO
import com.example.data.storage.models.MovieEntity
import com.example.domain.models.MovieDomainModel

fun MovieDTO.toDomainModel() = MovieDomainModel(
    adult = adult,
    backdropPath = backdropPath,
    id = id,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    title = title,
    voteAverage = voteAverage,
    voteCount = voteCount,
    isSelected = false
)

fun MovieDomainModel.toEntity() = MovieEntity(

    id = 0,
    adult = adult,
    backdropPath = backdropPath,
    movieID = id,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    releaseDate = releaseDate,
    title = title,
    isSelected = true,
    posterPath = posterPath,
    voteAverage = voteAverage,
    voteCount = voteCount
)

fun MovieEntity.toDomainModel() = MovieDomainModel(
    adult = adult,
    backdropPath = backdropPath,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    releaseDate = releaseDate,
    title = title,
    isSelected = true,
    posterPath = posterPath,
    voteAverage = voteAverage,
    voteCount = voteCount,
    id = movieID
)