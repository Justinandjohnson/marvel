package com.example.ye.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApiService {
    @GET("characters")
    fun getCharacters(
        @Query("apikey") apiKey: String,
        @Query("ts") timestamp: String,
        @Query("hash") hash: String,
        @Query("limit") limit: Int
    ): Single<CharactersResponse> // Using Single here

    // Update other methods to return RxJava types as needed
}
