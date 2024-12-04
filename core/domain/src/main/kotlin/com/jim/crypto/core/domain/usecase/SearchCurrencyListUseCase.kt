package com.jim.crypto.core.domain.usecase

import com.jim.crypto.core.data.repository.CurrencyRepository
import com.jim.crypto.core.model.data.CurrencyInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class SearchCurrencyListUseCase(
  private vararg val repositories: CurrencyRepository,
) {

  operator fun invoke(query: String): Flow<List<CurrencyInfo>> = combine(
    repositories.map { it.searchCurrencies(query) }
  ) { currencies ->
    currencies.asList().flatten()
  }
}