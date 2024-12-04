package com.jim.crypto.ui.currencylist

import androidx.lifecycle.ViewModel
import com.jim.crypto.core.data.repository.CryptoCurrencyRepository
import com.jim.crypto.core.data.repository.FiatCurrencyRepository
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
  private val cryptoCurrencyRepository: CryptoCurrencyRepository,
  private val fiatCurrencyRepository: FiatCurrencyRepository
) : ViewModel() {

  private val _uiState = MutableStateFlow(CurrencyListUiState())
  val uiState = _uiState.asStateFlow()

  fun getAllCurrencies(): Flow<List<CurrencyInfo>> =
    cryptoCurrencyRepository.getCryptoCurrencies("")

  fun searchCurrencies(query: String): Flow<List<CurrencyInfo>> =
    cryptoCurrencyRepository.getCryptoCurrencies(query)

//  private var fetchJob: Job? = null
//
//  init {
//    fetchData()
//  }
//
//  fun fetchData(query: String? = null) {
//    fetchJob?.cancel()
//    fetchJob = viewModelScope.launch {
//      try {
//        cryptoCurrencyRepository.getCryptoCurrencies()
//          .onEach { _uiState.value = _uiState.value.copy(items = it) }
////        _uiState.update {
////          it.copy(items = items)
////        }
//      } catch (ioe: IOException) {
//        _uiState.update {
//          it.copy(message = ioe.message)
//        }
//      }
//    }
//  }
}