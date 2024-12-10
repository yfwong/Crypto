package com.jim.crypto.core.domain

import com.jim.crypto.core.data.repository.FiatCurrencyRepository
import com.jim.crypto.core.model.data.CurrencyInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class SearchFiatCurrencyListUseCase(
  private val repository: FiatCurrencyRepository,
) {

  operator fun invoke(query: String): Flow<List<CurrencyInfo>> =
    repository.getItems(query).flowOn(Dispatchers.IO)
}
