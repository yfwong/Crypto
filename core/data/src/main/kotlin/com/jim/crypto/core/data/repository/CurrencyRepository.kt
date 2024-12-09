package com.jim.crypto.core.data.repository

import com.jim.crypto.core.model.data.CurrencyInfo
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {

  fun getItems(): Flow<List<CurrencyInfo>>

  fun getItems(query: String): Flow<List<CurrencyInfo>>

  suspend fun inertItems(currencies: List<CurrencyInfo>)

  suspend fun deleteItems()
}