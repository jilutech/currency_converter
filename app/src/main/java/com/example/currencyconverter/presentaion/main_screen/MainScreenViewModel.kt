package com.example.currencyconverter.presentaion.main_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.currencyconverter.domain.repo.CurrencyRepo
import com.example.currencyconverter.presentaion.main_screen.state_model.MainScreenEvent
import com.example.currencyconverter.presentaion.main_screen.state_model.MainScreenState

class MainScreenViewModel(
    private val currencyRepo : CurrencyRepo
) : ViewModel() {


    val sate by mutableStateOf(MainScreenState())

    fun onEvent(event: MainScreenEvent){
        when(event){
            is MainScreenEvent.BottomSheetItemClicked -> {

            }
            MainScreenEvent.FromCurrencySelected -> {

            }
            is MainScreenEvent.NumberButtonClicked -> {

            }
            MainScreenEvent.SwapIconClicked -> {

            }
            MainScreenEvent.ToCurrencySelected -> {
                
            }
        }
    }

}