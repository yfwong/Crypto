package com.jim.crypto.ui.currencylist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.jim.crypto.ui.component.SearchBar
import com.jim.crypto.ui.theme.Dimen

@Composable
fun CurrencyListScreen(viewModel: CurrencyListViewModel) {
  val currencies by viewModel.currencies.collectAsState()
  val isShowSearchInput by viewModel.isShowSearchInput.collectAsState()
  val query = viewModel.query

  LaunchedEffect(query) {
    if (isShowSearchInput) {
      viewModel.searchCurrencies(query)
    } else {
      viewModel.onQueryChange("")
      viewModel.getAllCurrencies()
    }
  }

  Column(modifier = Modifier.background(Color.LightGray)) {
    SearchView(isShowSearchInput, query, viewModel)
    CurrencyList(currencies = currencies, showDivider = isShowSearchInput)
    if (isShowSearchInput && currencies.isEmpty()) {
      EmptyState()
    }
  }
}

@Composable
fun SearchView(isSearchMode: Boolean, query: String, viewModel: CurrencyListViewModel) {
  if (isSearchMode) {
    SearchBar(
      query = query,
      onQueryChange = { viewModel.onQueryChange(it) },
      onNavigateBack = {
        viewModel.onQueryChange("")
        viewModel.showSearchInput(false)
      },
      onClear = { viewModel.onQueryChange("") })
  } else {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .clickable { viewModel.showSearchInput(true) }
        .padding(Dimen.Medium)
    ) {
      Icon(
        imageVector = Icons.Filled.Search,
        contentDescription = "Search"
      )
    }
  }
}