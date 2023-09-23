package com.example.currencyconverter.presentaion.main_screen.state_model

sealed class MainScreenEvent {

    object FromCurrencySelected : MainScreenEvent()
    object ToCurrencySelected : MainScreenEvent()
    object SwapIconClicked : MainScreenEvent()
    data class BottomSheetItemClicked (val value : String) : MainScreenEvent()
    data class NumberButtonClicked(val value: String) : MainScreenEvent()
}