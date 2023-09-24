package com.example.currencyconverter.data.repo

import com.example.currencyconverter.data.local.dao.CurrencyDao
import com.example.currencyconverter.data.local.entity.toCurrencyRate
import com.example.currencyconverter.data.local.entity.toCurrencyRateEntity
import com.example.currencyconverter.data.remote.CurrencyApi
import com.example.currencyconverter.data.remote.dto.toCurrencyRates
import com.example.currencyconverter.domain.model.CurrencyRate
import com.example.currencyconverter.domain.model.Resource
import com.example.currencyconverter.domain.repo.CurrencyRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.lang.Exception

class CurrencyRepoImpl(
    private val dao: CurrencyDao,
    private val repoApi: CurrencyApi
) : CurrencyRepo {
    override fun getCurrencyRateList(): Flow<Resource<List<CurrencyRate>>> = flow {

        val localCurrencyRate = getLocalCurrencyRates()
        emit(Resource.Success(localCurrencyRate))

        try {
             val newRates = getRemoteCurrencyRates()
            updateLocalDb(newRates)
            emit(Resource.Success(newRates))
        }catch (e : IOException){
            emit(Resource.Error("Check the internet", data = localCurrencyRate))
        }catch (e : Exception){
            emit(Resource.Error("Check the internet", data = localCurrencyRate))
        }

    }

    private suspend fun getRemoteCurrencyRates() : List<CurrencyRate>{
        val response = repoApi.getLatestRates()
        return response.toCurrencyRates()
    }
    private suspend fun getLocalCurrencyRates() : List<CurrencyRate>{
            return dao.getAllCurrencyRates().map { it.toCurrencyRate() }
    }

    private suspend fun updateLocalDb(newRates: List<CurrencyRate>) {
        dao.up_sert_all(newRates.map { it.toCurrencyRateEntity() })
    }
}