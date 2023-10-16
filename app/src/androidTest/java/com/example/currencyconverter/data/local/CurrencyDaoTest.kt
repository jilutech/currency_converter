package com.example.currencyconverter.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.currencyconverter.data.local.dao.CurrencyDao
import com.example.currencyconverter.data.local.db.CurrencyDB
import com.example.currencyconverter.data.local.entity.CurrencyRatesEntity
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat


@RunWith(AndroidJUnit4::class)
@SmallTest
class CurrencyDaoTest {

    private lateinit var currencyDB: CurrencyDB
    private lateinit var dao: CurrencyDao


    @Before
    fun setUp(){
        currencyDB = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CurrencyDB::class.java
        ).allowMainThreadQueries().build()

        dao = currencyDB.currencyRateDao
    }

    @After
    fun tearDown(){
        currencyDB.close()
    }


    @Test
    fun upsert_all_item() = runTest {
        val currencyRatesEntity = CurrencyRatesEntity("USA","INR",11.1)
        dao.insert(currencyRatesEntity)
        dao.delete(currencyRatesEntity)
        val all_curency = dao.getAllCurrencyRates()
        assertThat(all_curency).doesNotContain(currencyRatesEntity)
//        assertThat(all_curency).contains(currencyRatesEntity)




    }
}