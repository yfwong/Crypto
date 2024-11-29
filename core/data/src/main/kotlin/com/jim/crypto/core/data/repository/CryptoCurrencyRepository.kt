package com.jim.crypto.core.data.repository

import com.jim.crypto.core.model.data.CurrencyInfo

interface CryptoCurrencyRepository {

  suspend fun getCryptoCurrencies(query: String? = null): List<CurrencyInfo>

  suspend fun addCryptoCurrencies(currencies: List<CurrencyInfo>)

  suspend fun deleteCryptoCurrencies()
}
