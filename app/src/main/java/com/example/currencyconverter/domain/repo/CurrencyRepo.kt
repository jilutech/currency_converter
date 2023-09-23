package com.example.currencyconverter.domain.repo

import com.example.currencyconverter.domain.model.CurrencyRate
import com.example.currencyconverter.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface CurrencyRepo {

    fun getCurrencyRateList () : Flow<Resource<List<CurrencyRate>>>

}