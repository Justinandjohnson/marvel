package com.example.ye.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ye.repository.MarvelRepository
import io.reactivex.rxjava3.core.Single
import com.example.ye.model.CharactersResponse
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class CharactersViewModel(private val repository: MarvelRepository) : ViewModel() {

    private val disposables = CompositeDisposable()

    fun getCharacters(apiKey: String, timestamp: String, hash: String, limit: Int): Single<CharactersResponse> {
        return repository.getCharacters(apiKey, timestamp, hash, limit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()) // UI updates should be done on the main thread
    }

    override fun onCleared() {
        disposables.clear()
    }
}
