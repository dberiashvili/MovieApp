package com.example.data.mappers

import com.example.data.network.dto.MovieDTO
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
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount,
    isSelected = false
)