package com.jim.crypto.core.data.repository

import androidx.paging.PagingData
import com.jim.crypto.core.model.data.CurrencyInfo
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {

  fun getPagedItems(query: String): Flow<PagingData<CurrencyInfo>>

  suspend fun inertItems(currencies: List<CurrencyInfo>)

  suspend fun deleteItems()
}