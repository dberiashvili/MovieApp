package com.example.data.network.service

import com.example.data.network.dto.ResponseDTO
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieService {

    @GET("popular")
    fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Observable<ResponseDTO>
}
