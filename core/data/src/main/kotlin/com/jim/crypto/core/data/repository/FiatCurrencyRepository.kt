package com.jim.crypto.core.data.repository

import com.jim.crypto.core.model.data.FiatCurrency
import kotlinx.coroutines.flow.Flow

interface FiatCurrencyRepository {

  fun getFiatCurrencies(
    query: String? = null
  ): Flow<List<FiatCurrency>>

  suspend fun addFiatCurrencies(currencies: List<FiatCurrency>)

  suspend fun deleteFiatCurrencies()
}
