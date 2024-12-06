package com.jim.crypto.ui.currencylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jim.crypto.core.domain.usecase.SearchCurrencyListUseCase
import com.jim.crypto.core.model.data.CurrencyInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn

class CurrencyListViewModel(
  private val searchCurrencyListUseCase: SearchCurrencyListUseCase
) : ViewModel() {

  private val _isShowSearchInput = MutableStateFlow(false)
  val isShowSearchInput: StateFlow<Boolean> = _isShowSearchInput

  private val _searchQuery = MutableStateFlow("")
  val searchQuery: StateFlow<String> = _searchQuery

  @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
  val pagedItems: Flow<PagingData<CurrencyInfo>> = _searchQuery
    .debounce(300) // To handle quick typing
    .distinctUntilChanged() // Avoid unnecessary updates
    .flatMapLatest { query -> searchCurrencyListUseCase(query).flowOn(Dispatchers.IO) }
    .cachedIn(viewModelScope)

  fun onQueryChange(newQuery: String) {
    _searchQuery.value = newQuery
  }

  fun showSearchInput(show: Boolean) {
    _isShowSearchInput.value = show
  }
}