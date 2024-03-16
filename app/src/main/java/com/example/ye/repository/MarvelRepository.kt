package com.example.ye.repository

import com.example.ye.network.MarvelApiService
import com.example.ye.util.MarvelAuthUtils
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class MarvelRepository(private val marvelApiService: MarvelApiService) {

    fun getCharacters(apiKey: String, privateKey: String, limit: Int): Single<CharactersResponse> {
        val timestamp = System.currentTimeMillis().toString()
        val hash = MarvelAuthUtils.generateMarvelHash(timestamp, apiKey, privateKey)

        return marvelApiService.getCharacters(apiKey, timestamp, hash, limit)
            .subscribeOn(Schedulers.io())
    }
}
