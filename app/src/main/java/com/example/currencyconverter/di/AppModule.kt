package com.example.currencyconverter.di

import android.app.Application
import androidx.room.Room
import com.example.currencyconverter.Constant.BASE_URL
import com.example.currencyconverter.data.local.dao.CurrencyDao
import com.example.currencyconverter.data.local.db.CurrencyDB
import com.example.currencyconverter.data.remote.CurrencyApi
import com.example.currencyconverter.data.repo.CurrencyRepoImpl
import com.example.currencyconverter.domain.repo.CurrencyRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideCurrencyApi() : CurrencyApi{

        val retrofit = Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(CurrencyApi::class.java)
    }


    @Provides
    @Singleton
    fun providesDataBase(application: Application) : CurrencyDB{
        return Room
            .databaseBuilder(application,
            CurrencyDB::class.java,
            "currency_db").build()
    }

    @Provides
    @Singleton
    fun provideCurrencyRepo(
        api: CurrencyApi,
        db : CurrencyDB
    ):CurrencyRepo{
        return CurrencyRepoImpl(
            repoApi = api,
            dao = db.currencyRateDao
        )
    }
}