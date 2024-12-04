package com.jim.crypto.ui.currencylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jim.crypto.core.domain.usecase.GetCurrencyListUseCase
import com.jim.crypto.core.domain.usecase.SearchCurrencyListUseCase
import com.jim.crypto.core.model.data.CurrencyInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CurrencyListViewModel(
  private val getCurrencyListUseCase: GetCurrencyListUseCase,
  private val searchCurrencyListUseCase: SearchCurrencyListUseCase
) : ViewModel() {

  private val _currencies = MutableStateFlow<List<CurrencyInfo>>(emptyList())
  val currencies: StateFlow<List<CurrencyInfo>> = _currencies

  private val _isSearching = MutableStateFlow(false)
  val isSearching: StateFlow<Boolean> = _isSearching

  fun getAllCurrencies() {
    viewModelScope.launch(Dispatchers.IO) {
      getCurrencyListUseCase().collect {
        _currencies.value = it
      }
    }
  }

  fun searchCurrencies(query: String) {
    viewModelScope.launch(Dispatchers.IO) {
      searchCurrencyListUseCase(query).collect {
        _currencies.value = it
      }
    }
  }

  fun startSearchingCurrencies() {
    _isSearching.value = true
  }

  fun stopSearchCurrencies() {
    _isSearching.value = false
  }
}