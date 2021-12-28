package com.example.currencyconverter.data.remote.network

import com.example.currencyconverter.BuildConfig
import com.example.currencyconverter.data.remote.response.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("{apiKey}/pair/{currency1}/{currency2}/{amount}")
    suspend fun getCurrency(
        @Path("apiKey") apiKey : String = BuildConfig.API_KEY,
        @Path("currency1") currency1 : String ,
        @Path("currency2") currency2 : String ,
        @Path("amount") amount : Double
    ) : Response<CurrencyResponse>
}