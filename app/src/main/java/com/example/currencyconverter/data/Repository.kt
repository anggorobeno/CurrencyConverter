package com.example.currencyconverter.data

import com.example.currencyconverter.data.remote.network.ApiHelper
import com.example.currencyconverter.data.remote.response.CurrencyResponse
import com.example.currencyconverter.data.utils.Resource
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor (private val apiService: ApiHelper) {
    suspend fun getCurrency(currency1: String, currency2: String,amount: Double): Resource<CurrencyResponse>{
        val response = apiService.getCurrency(currency1 = currency1,currency2 = currency2,amount = amount)
        val result = response.body()
        return if (response.isSuccessful && result != null){
            Resource.Succes<CurrencyResponse>(result)
        } else{
            Resource.Error(response.message())
        }

    }
    suspend fun getS(
        currency1: String,
        currency2: String,
        amount: Double
    ): Response<CurrencyResponse> {
        return apiService.getCurrency(currency1 = currency1, currency2 = currency2, amount = amount)

    }
}
