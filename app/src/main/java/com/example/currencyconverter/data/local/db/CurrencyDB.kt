package com.example.currencyconverter.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.currencyconverter.data.local.dao.CurrencyDao
import com.example.currencyconverter.data.local.entity.CurrencyRatesEntity


@Database(
    entities = [CurrencyRatesEntity::class],
    version = 1, exportSchema = false
)
abstract class CurrencyDB : RoomDatabase() {
    abstract val currencyRateDao : CurrencyDao
}