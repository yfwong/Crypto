package com.jim.crypto.core.domain.usecase

import android.util.Log
import androidx.paging.PagingData
import androidx.paging.map
import com.jim.crypto.core.data.repository.CurrencyRepository
import com.jim.crypto.core.model.data.CurrencyInfo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class SearchCurrencyListUseCase(
  private vararg val repositories: CurrencyRepository,
) {

//    @OptIn(ExperimentalCoroutinesApi::class)
//  operator fun invoke(query: String): Flow<PagingData<CurrencyInfo>> =
//    flowOf(*repositories) // Create a flow of the repositories
//      .flatMapMerge { repository ->
//        repository.searchCurrencies(query = query) // Each repository's flow of PagingData
//      }
//      .map { pagingDataList ->
//        pagingDataList.listIterator().forEach {
//          Log.d("JIMWONG", ">> $it")
//        }
//        PagingData.from(pagingDataList) // Combine into a PagingData
//      }

  operator fun invoke(query: String): Flow<PagingData<CurrencyInfo>> =
    repositories
      .map { it.getPagedItems(query) }
      .reduce { pagingDataFlow1, pagingDataFlow2 ->
        // Combine both flows into a single Flow
        Log.e("JIMWONG", "B")
        combine(
          pagingDataFlow1,
          pagingDataFlow2
        ) { pagingData1, pagingData2 ->
          // Merging the PagingData into one list
          val mergedData = mutableListOf<CurrencyInfo>()
          pagingData1.map { mergedData.add(it) }
          pagingData2.map { mergedData.add(it) }
          mergedData.map { Log.e("JIMWONG", ">> $it") }
          // Return the merged PagingData
          PagingData.from(mergedData)
        }
      }
}