package com.jim.crypto.core.data.repository

import com.jim.crypto.core.model.data.CryptoCurrency
import kotlinx.coroutines.flow.Flow

interface CryptoCurrencyRepository {

  fun getCryptoCurrencies(
    query: String? = null
  ): Flow<List<CryptoCurrency>>

  suspend fun addCryptoCurrencies(currencies: List<CryptoCurrency>)

  suspend fun deleteCryptoCurrencies()
}
