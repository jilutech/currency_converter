package com.example.currencyconverter.data.remote

import com.example.currencyconverter.Constant.API_KEY
import com.example.currencyconverter.data.remote.dto.CurrencyDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET("v1/latest")
    suspend fun getLatestRates(
        @Query("apikey") apikey : String = API_KEY
    ) : CurrencyDto


}