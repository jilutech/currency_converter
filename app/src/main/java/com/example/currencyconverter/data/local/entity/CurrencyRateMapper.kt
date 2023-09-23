package com.example.currencyconverter.data.local.entity

import com.example.currencyconverter.domain.model.CurrencyRate

fun  CurrencyRatesEntity.toCurrencyRate() : CurrencyRate {
    return CurrencyRate(
        code = code,
        name = name,
        rate = rate
    )
}
fun  CurrencyRate.toCurrencyRateEntity() : CurrencyRatesEntity {
    return CurrencyRatesEntity(
        code = code,
        name = name,
        rate = rate
    )
}