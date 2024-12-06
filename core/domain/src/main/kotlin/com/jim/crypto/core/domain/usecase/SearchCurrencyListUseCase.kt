package com.jim.crypto.core.domain.usecase

import androidx.paging.PagingData
import com.jim.crypto.core.data.repository.CurrencyRepository
import com.jim.crypto.core.model.data.CurrencyInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class SearchCurrencyListUseCase(
  private val repository: CurrencyRepository,
) {

  operator fun invoke(query: String): Flow<PagingData<CurrencyInfo>> =
    repository.getPagedItems(query).flowOn(Dispatchers.IO)
}
