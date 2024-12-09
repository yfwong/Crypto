package com.jim.crypto.core.ui.currencylist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.jim.crypto.core.ui.component.SearchBar
import com.jim.crypto.core.ui.theme.Dimens

@Composable
fun SearchView(
  isSearchMode: Boolean, query: String,
  onSearchClick: () -> Unit,
  onQueryChange: (String) -> Unit,
  onQueryClear: () -> Unit,
  onNavigateBack: () -> Unit
) {
  if (isSearchMode) {
    SearchBar(
      query = query,
      onQueryChange = onQueryChange,
      onNavigateBack = onNavigateBack,
      onQueryClear = onQueryClear
    )
  } else {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .clickable { onSearchClick() }
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