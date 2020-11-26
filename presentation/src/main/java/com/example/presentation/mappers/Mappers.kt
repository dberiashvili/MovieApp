package com.example.presentation.mappers

import com.example.domain.models.MovieDomainModel
import com.example.presentation.models.MoviePresentationModel

fun MovieDomainModel.toPresentationModel() = MoviePresentationModel(
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
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount,
    isSelected = false
)

fun MoviePresentationModel.toDomainModel() = MovieDomainModel(
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
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount,
    isSelected = isSelected
)