package com.example.currencyconverter.data

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.*
import com.example.currencyconverter.data.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private  var currency1: String = ""
    private  var currency2: String = ""
    private var amount: Double = 0.0


    fun setCurrency(currency1: String, currency2: String, amount: Double) {
        this.currency1 = currency1
        this.currency2 = currency2
        this.amount = amount
    }

    fun getResult(): LiveData<Resource<Double>>{
        return liveData(Dispatchers.IO) {
            emit(Resource.Loading<Double>())
            val response = repository.getS(
                currency1 = currency1,
                currency2 = currency2,
                amount = amount
            )
            val result = response.body()
            if (response.isSuccessful && result != null){
                result.conversionResult?.let {
                    emit(Resource.Succes(it))
                }
            }
            else emit(Resource.Error<Double>(response.code().toString()))
        }
    }
}