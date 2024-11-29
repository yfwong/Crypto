package com.jim.crypto.core.data.repository

import com.jim.crypto.core.model.data.CurrencyInfo

interface FiatCurrencyRepository {

  suspend fun getFiatCurrencies(query: String? = null): List<CurrencyInfo>

  suspend fun addFiatCurrencies(currencies: List<CurrencyInfo>)

  suspend fun deleteFiatCurrencies()
}
