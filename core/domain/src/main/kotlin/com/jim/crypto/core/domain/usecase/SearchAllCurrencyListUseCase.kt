package com.jim.crypto.core.domain.usecase

import com.jim.crypto.core.data.repository.AllCurrencyRepository
import com.jim.crypto.core.model.data.CurrencyInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class SearchAllCurrencyListUseCase(
  private val repository: AllCurrencyRepository,
) {

  operator fun invoke(query: String): Flow<List<CurrencyInfo>> =
    repository.getItems(query).flowOn(Dispatchers.IO)
}
