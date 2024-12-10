package com.jim.crypto.core.data.repository

import com.jim.crypto.core.model.data.CurrencyInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class InMemoryCurrencyRepository {

  private val _currencies = MutableStateFlow<List<CurrencyInfo>>(emptyList())

  fun getItems(query: String): Flow<List<CurrencyInfo>> =
    _currencies.map { it.filterByQuery(query) }

  suspend fun setItems(currencies: List<CurrencyInfo>) {
    _currencies.value = currencies
  }

  private fun List<CurrencyInfo>.filterByQuery(query: String): List<CurrencyInfo> {
    val trimmedQuery = query.trim()
    return this.filter { currency ->
      val lowerName = currency.name.lowercase()
      val lowerSymbol = currency.symbol.lowercase()
      val lowerQuery = trimmedQuery.lowercase()

      lowerName.startsWith(lowerQuery) || // Matches `name LIKE :query || '%'`
          lowerName.contains(" $lowerQuery") || // Matches `name LIKE '% ' || :query || '%'`
          lowerSymbol.startsWith(lowerQuery) // Matches `symbol LIKE :query || '%'`
    }
  }
}
