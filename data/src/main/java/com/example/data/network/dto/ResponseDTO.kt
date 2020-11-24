package com.example.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseDTO(
    val page:Int,
    @SerialName("total_results")
    val totalResults:Int,
    @SerialName("total_pages")
    val totalPages:Int,
    val results:List<MovieDTO>
)