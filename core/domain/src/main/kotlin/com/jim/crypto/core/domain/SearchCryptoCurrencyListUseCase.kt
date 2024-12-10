package com.jim.crypto.core.domain

import com.jim.crypto.core.data.repository.CryptoCurrencyRepository
import com.jim.crypto.core.model.data.CurrencyInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class SearchCryptoCurrencyListUseCase(
  private val repository: CryptoCurrencyRepository,
) {

  operator fun invoke(query: String): Flow<List<CurrencyInfo>> =
    repository.getItems(query).flowOn(Dispatchers.IO)
}
