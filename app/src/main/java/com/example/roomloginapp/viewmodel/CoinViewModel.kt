package com.example.roomloginapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.roomloginapp.data.model.Coin
import com.example.roomloginapp.data.repository.CoinRepository
import com.example.roomloginapp.data.retrofit.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    application: Application,
    private val coinRepository: CoinRepository
) : AndroidViewModel(application) {

    private val coins: MutableLiveData<List<Coin>> by lazy {
        MutableLiveData<List<Coin>>()
    }

    init {
        getCoins()
    }

    // Get coins list data from repository
    private fun getCoins() {
        viewModelScope.launch {
            coins.value = coinRepository.getCoins()
        }
    }

    // Returns coins list LiveData
    fun coins(): LiveData<List<Coin>> = coins
}