package com.jim.crypto.core.domain.usecase

import androidx.paging.PagingData
import com.jim.crypto.core.data.repository.CurrencyRepository
import com.jim.crypto.core.model.data.CurrencyInfo
import kotlinx.coroutines.flow.Flow

class SearchCurrencyListUseCase(
  private val repository: CurrencyRepository,
) {
  operator fun invoke(query: String): Flow<PagingData<CurrencyInfo>> =
    repository.getPagedItems(query)

}
