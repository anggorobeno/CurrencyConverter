package com.example.currencyconverter.data.remote.network

import com.example.currencyconverter.BuildConfig
import com.example.currencyconverter.data.remote.response.CurrencyResponse
import retrofit2.Response
import retrofit2.http.Path
import javax.inject.Inject

class ApiHelper @Inject constructor(private val apiService: ApiService) {
    suspend fun getCurrency(
        apiKey : String = BuildConfig.API_KEY,
        currency1 : String,
        currency2 : String,
         amount : Double
    ) : Response<CurrencyResponse> {
        return apiService.getCurrency(apiKey, currency1, currency2, amount)
    }
}