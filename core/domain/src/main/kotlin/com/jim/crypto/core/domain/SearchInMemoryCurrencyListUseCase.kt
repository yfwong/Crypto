package com.jim.crypto.core.domain

import com.jim.crypto.core.data.repository.InMemoryCurrencyRepository
import com.jim.crypto.core.model.data.CurrencyInfo
import kotlinx.coroutines.flow.Flow

class SearchInMemoryCurrencyListUseCase(
  private val repository: InMemoryCurrencyRepository,
) {
  operator fun invoke(query: String): Flow<List<CurrencyInfo>> =
    repository.getItems(query)
}
