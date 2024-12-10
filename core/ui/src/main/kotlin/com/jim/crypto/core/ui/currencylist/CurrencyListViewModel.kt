package com.jim.crypto.core.ui.currencylist

import androidx.lifecycle.ViewModel
import com.jim.crypto.core.domain.SearchCurrencyListUseCase
import com.jim.crypto.core.domain.SetCurrencyListUseCase
import com.jim.crypto.core.model.data.CurrencyInfo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest

class CurrencyListViewModel(
  private val setCurrencyListUseCase: SetCurrencyListUseCase,
  private val searchCurrencyListUseCase: SearchCurrencyListUseCase
) : ViewModel() {

  private val _isShowSearchInput = MutableStateFlow(false)
  val isShowSearchInput: StateFlow<Boolean> = _isShowSearchInput.asStateFlow()

  private val _searchQuery = MutableStateFlow("")
  val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

  @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
  val pagedItems: Flow<List<CurrencyInfo>> = _searchQuery
    .debounce(SEARCH_DEBOUNCE_MS) // To handle quick typing
    .distinctUntilChanged() // Avoid unnecessary updates
    .flatMapLatest { query ->
      searchCurrencyListUseCase(query)
    }

  fun initializeCurrencyList(currencies: List<CurrencyInfo>) {
    setCurrencyListUseCase(currencies)
  }

  fun onQueryChange(newQuery: String) {
    _searchQuery.value = newQuery
  }

  fun onQueryClear() {
    _searchQuery.value = ""
  }

  fun onNavigateBack() {
    _searchQuery.value = ""
    _isShowSearchInput.value = false
  }

  fun onSearchClick() {
    _isShowSearchInput.value = true
  }

  companion object {
    const val SEARCH_DEBOUNCE_MS = 200L
  }
}