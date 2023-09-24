package com.example.currencyconverter.presentaion.main_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.domain.model.Resource
import com.example.currencyconverter.domain.repo.CurrencyRepo
import com.example.currencyconverter.presentaion.main_screen.state_model.MainScreenEvent
import com.example.currencyconverter.presentaion.main_screen.state_model.MainScreenState
import com.example.currencyconverter.presentaion.main_screen.state_model.SelectionSate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val currencyRepo : CurrencyRepo
) : ViewModel() {


    var state by mutableStateOf(MainScreenState())

    init {
        getCurrencyRatesList()
    }

    fun onEvent(event: MainScreenEvent) {
        when(event) {
            MainScreenEvent.FromCurrencySelected -> {
                state = state.copy(selection = SelectionSate.FROM)
            }
            MainScreenEvent.ToCurrencySelected -> {
                state = state.copy(selection = SelectionSate.TO)
            }
            MainScreenEvent.SwapIconClicked -> {
                state = state.copy(
                    fromCurrencyCode = state.toCurrencyCode,
                    fromCurrencyValue = state.toCurrencyValue,
                    toCurrencyCode = state.fromCurrencyCode,
                    toCurrencyValue = state.fromCurrencyValue
                )
            }
            is MainScreenEvent.NumberButtonClicked -> {
                updateCurrencyValue(value = event.value)
            }
            is MainScreenEvent.BottomSheetItemClicked -> {
                if (state.selection == SelectionSate.FROM) {
                    state = state.copy(fromCurrencyCode = event.value)
                } else if (state.selection == SelectionSate.TO) {
                    state = state.copy(toCurrencyCode = event.value)
                }
                updateCurrencyValue("")
            }
        }
    }

    private fun getCurrencyRatesList() {
        viewModelScope.launch {
            currencyRepo
                .getCurrencyRateList()
                .collectLatest { result ->
                    state = when(result) {
                        is Resource.Success -> {
                            state.copy(
                                currencyRates = result.data?.associateBy { it.code } ?: emptyMap(),
                                error = null
                            )
                        }

                        is Resource.Error -> {
                            state.copy(
                                currencyRates = result.data?.associateBy { it.code } ?: emptyMap(),
                                error = result.message
                            )
                        }
                    }
                }
        }
    }

    private fun updateCurrencyValue(value: String) {

        val currentCurrencyValue = when(state.selection) {
            SelectionSate.FROM -> state.fromCurrencyValue
            SelectionSate.TO -> state.toCurrencyValue
        }
        val fromCurrencyRate = state.currencyRates[state.fromCurrencyCode]?.rate ?: 0.0
        val toCurrencyRate = state.currencyRates[state.toCurrencyCode]?.rate ?: 0.0

        val updatedCurrencyValue = when(value) {
            "C" -> "0.00"
            else -> if (currentCurrencyValue == "0.00") value else currentCurrencyValue + value
        }

        val numberFormat = DecimalFormat("#.00")

        when(state.selection) {
            SelectionSate.FROM -> {
                val fromValue = updatedCurrencyValue.toDoubleOrNull() ?: 0.0
                val toValue = fromValue / fromCurrencyRate * toCurrencyRate
                state = state.copy(
                    fromCurrencyValue = updatedCurrencyValue,
                    toCurrencyValue = numberFormat.format(toValue)
                )
            }
            SelectionSate.TO -> {
                val toValue = updatedCurrencyValue.toDoubleOrNull() ?: 0.0
                val fromValue = toValue / toCurrencyRate * fromCurrencyRate
                state = state.copy(
                    toCurrencyValue = updatedCurrencyValue,
                    fromCurrencyValue = numberFormat.format(fromValue)
                )
            }
        }
    }
}