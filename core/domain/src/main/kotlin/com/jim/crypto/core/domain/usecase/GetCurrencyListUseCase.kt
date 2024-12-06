package com.jim.crypto.core.domain.usecase

import androidx.paging.PagingData
import com.jim.crypto.core.data.repository.CurrencyRepository
import com.jim.crypto.core.model.data.CurrencyInfo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class GetCurrencyListUseCase(
  private vararg val repositories: CurrencyRepository
) {

  @OptIn(ExperimentalCoroutinesApi::class)
  operator fun invoke(): Flow<PagingData<CurrencyInfo>> =
    flowOf(*repositories) // Create a flow of the repositories
      .flatMapMerge { repository ->
        repository.searchCurrencies(query = "") // Each repository's flow of PagingData
      }
      .map { pagingDataList ->
        PagingData.from(pagingDataList) // Combine into a PagingData
      }
}