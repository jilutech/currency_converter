package com.example.currencyconverter.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.currencyconverter.data.local.db.CurrencyDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Named("test_db")
    fun provideInMemoryDb(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(context, CurrencyDB::class.java)
            .allowMainThreadQueries()
            .build()
}