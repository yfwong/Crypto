package com.jim.crypto.core.data.repository

import com.jim.crypto.core.model.data.CurrencyInfo
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {

  fun getCurrencies(query: String): Flow<List<CurrencyInfo>>

  suspend fun addCurrencies(currencies: List<CurrencyInfo>)

  suspend fun deleteCurrencies()
}