package com.jim.crypto.core.ui.currencylist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.paging.compose.collectAsLazyPagingItems
import com.jim.crypto.core.ui.component.SearchBar
import com.jim.crypto.core.ui.theme.Dimens
import com.jim.crypto.core.ui.utils.isEmpty
import com.jim.crypto.core.ui.utils.isEndOfData
import com.jim.crypto.core.ui.utils.isLoading

@Composable
fun CurrencyListScreen(viewModel: CurrencyListViewModel) {
  val isShowSearchInput by viewModel.isShowSearchInput.collectAsState()
  val query by viewModel.searchQuery.collectAsState()
  val items = viewModel.pagedItems.collectAsLazyPagingItems()

  Column(modifier = Modifier) {
    SearchView(isShowSearchInput, query, viewModel)
    if (items.isEmpty()) {
      EmptyView(isShowSearchInput)
    } else {
      LazyColumn {
        items(
          count = items.itemCount,
          key = { index -> items[index]?.id ?: "fallback-$index" }
        ) { index ->
          items[index]?.let { item ->
            CurrencyItem(item)
            HorizontalDivider(color = if (isShowSearchInput) Color.Gray else Color.Transparent)
          }
        }

        if (items.isLoading()) {
          item {
            LoadingView()
          }
        } else if (items.isEndOfData()) {
          item {
            EndOfDataFooterView()
          }
        }
      }
    }
  }
}

@Composable
fun SearchView(isSearchMode: Boolean, query: String, viewModel: CurrencyListViewModel) {
  if (isSearchMode) {
    SearchBar(
      query = query,
      onQueryChange = viewModel::onQueryChange,
      onNavigateBack = viewModel::onNavigateBack,
      onQueryClear = viewModel::onQueryClear
    )
  } else {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .clickable { viewModel.onSearchClick() }
        .background(Color.LightGray)
        .padding(Dimens.Medium)
    ) {
      Icon(
        imageVector = Icons.Filled.Search,
        contentDescription = "Search"
      )
    }
  }
}

@Composable
fun EmptyView(isShowSearchInput: Boolean) {
  if (isShowSearchInput)
    EmptySearchResultView()
  else
    EmptyNormalView()
}
