package com.jim.crypto.core.data.repository

import com.jim.crypto.core.model.data.CurrencyInfo
import kotlinx.coroutines.flow.Flow

interface FiatCurrencyRepository {

  fun getFiatCurrencies(query: String): Flow<List<CurrencyInfo>>

  suspend fun addFiatCurrencies(currencies: List<CurrencyInfo>)

  suspend fun deleteFiatCurrencies()
}
