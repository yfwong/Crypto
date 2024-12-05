package com.jim.crypto.ui.currencylist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.jim.crypto.ui.component.SearchBar
import com.jim.crypto.ui.component.SearchButton
import com.jim.crypto.ui.theme.Dimen

@Composable
fun CurrencyListScreen(viewModel: CurrencyListViewModel) {
  val currencies by viewModel.currencies.collectAsState()
  val isSearching by viewModel.isSearching.collectAsState()
  val query = viewModel.query

  LaunchedEffect(query) {
    if (isSearching) {
      viewModel.searchCurrencies(query)
    } else {
      viewModel.onQueryChange("")
      viewModel.getAllCurrencies()
    }
  }

  Column(modifier = Modifier.background(Color.LightGray)) {
    if (isSearching) {
      SearchBar(
        query = query,
        onQueryChange = { viewModel.onQueryChange(it) },
        onNavigateBack = {
          viewModel.onQueryChange("")
          viewModel.stopSearchCurrencies()
        },
        onClear = { viewModel.onQueryChange("") })
    } else {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = Dimen.Tiny)
      ) {
        SearchButton(onClick = { viewModel.startSearchingCurrencies() })
      }
    }
    CurrencyList(currencies = currencies, showDivider = isSearching)
    if (isSearching && currencies.isEmpty()) {
      EmptyState()
    }
  }
}