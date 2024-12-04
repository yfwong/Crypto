package com.jim.crypto.ui.currencylist

import androidx.lifecycle.ViewModel
import com.jim.crypto.core.data.repository.CurrencyRepository
import com.jim.crypto.core.model.data.CurrencyInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class CurrencyListUiState(
  val items: List<CurrencyInfo> = emptyList(),
  val loading: Boolean = false,
  val message: String? = null
)

class CurrencyListViewModel(
  private val currencyRepository: CurrencyRepository
) : ViewModel() {

  private val _uiState = MutableStateFlow(CurrencyListUiState())
  val uiState = _uiState.asStateFlow()

  fun getAllCurrencies(): Flow<List<CurrencyInfo>> =
    currencyRepository.getCurrencies("")

  fun searchCurrencies(query: String): Flow<List<CurrencyInfo>> =
    currencyRepository.getCurrencies(query)
}