package com.example.currencyconverter.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.example.currencyconverter.data.local.db.CurrencyDB
import com.example.currencyconverter.data.local.entity.CurrencyRatesEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface CurrencyDao {

    @Upsert
    suspend fun up_sert_all(currencyRate : List<CurrencyRatesEntity>)
    @Delete
    suspend fun delete(currencyRate : CurrencyRatesEntity)

    @Insert
    suspend fun insert(currencyDB: CurrencyRatesEntity)

    @Query("SELECT * FROM currencyratesentity")
    suspend fun getAllCurrencyRates() :  List<CurrencyRatesEntity>
}