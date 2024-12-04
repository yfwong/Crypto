package com.jim.crypto.core.data.repository

import com.jim.crypto.core.model.data.CurrencyInfo
import kotlinx.coroutines.flow.Flow

interface CryptoCurrencyRepository {

  fun getCryptoCurrencies(query: String): Flow<List<CurrencyInfo>>

  suspend fun addCryptoCurrencies(currencies: List<CurrencyInfo>)

  suspend fun deleteCryptoCurrencies()
}
