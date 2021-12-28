package com.example.currencyconverter.data

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private lateinit var currency1: String
    private lateinit var currency2: String
    private  var amount: Double = 0.0

    val tes : MutableLiveData<Double> = MutableLiveData()
    fun setCurrency(currency1: String,currency2: String,amount: Double){
        this.currency1 = currency1
        this.currency2 = currency2
        this.amount = amount

    }


    fun getCurrency(): LiveData<Double> {
        return liveData {
            repository.getCurrency(
                currency1 = currency1,
                currency2 = currency2,
                amount = amount
            ).conversionResult?.let { this.emit(it) }
        }
    }

}