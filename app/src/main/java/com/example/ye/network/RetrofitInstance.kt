package com.example.ye.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://gateway.marvel.com/v1/public/"

    private val moshi: Moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())  // Changed to addLast to ensure it's added last
        .build()

    // This will create Retrofit and Moshi instances when first accessed
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    // Expose a lazy-initialized MarvelApiService based on the Retrofit instance
    val marvelApiService: MarvelApiService by lazy {
        retrofit.create(MarvelApiService::class.java)
    }
}
