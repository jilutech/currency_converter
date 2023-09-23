package com.example.currencyconverter.data.repo

import com.example.currencyconverter.data.local.dao.CurrencyDao
import com.example.currencyconverter.domain.model.CurrencyRate
import com.example.currencyconverter.domain.model.Resource
import com.example.currencyconverter.domain.repo.CurrencyRepo
import kotlinx.coroutines.flow.Flow

class CurrencyRepoImpl(
    private val dao: CurrencyDao,
    private val repo: CurrencyRepo
) : CurrencyRepo {
    override fun getCurrencyRateList(): Flow<Resource<List<CurrencyRate>>> {
        TODO("Not yet implemented")
    }
}