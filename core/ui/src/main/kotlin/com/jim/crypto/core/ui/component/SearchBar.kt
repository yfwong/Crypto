package com.jim.crypto.core.ui.component

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jim.crypto.core.ui.R

@Composable
fun SearchBar(
  query: String,
  onQueryChange: (String) -> Unit,
  placeholder: String = stringResource(R.string.search_placeholder),
  autoFocus: Boolean = true,
  onNavigateBack: () -> Unit = {},
  onClear: () -> Unit = {}
) {
  val focusRequester = remember { FocusRequester() }

  if (autoFocus) {
    LaunchedEffect(Unit) {
      focusRequester.requestFocus()
    }
  }

  BackHandler(onBack = onNavigateBack)

  TextField(
    value = query,
    onValueChange = onQueryChange,
    placeholder = { Text(placeholder) },
    leadingIcon = {
      IconButton(onClick = onNavigateBack) {
        Icon(
          imageVector = Icons.AutoMirrored.Filled.ArrowBack,
          contentDescription = "Search"
        )
      }
    },
    trailingIcon = {
      if (query.isNotEmpty()) {
        IconButton(onClick = onClear) {
          Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Clear"
          )
        }
      }
    },
    singleLine = true,
    modifier = Modifier
      .fillMaxWidth()
      .focusRequester(focusRequester)
  )
}

@Preview(showBackground = true)
@Composable
fun PreviewSearchBarWithoutText() {
  SearchBar(
    query = "",
    onQueryChange = {},
    onNavigateBack = {}
  )
}

@Preview(showBackground = true)
@Composable
fun PreviewSearchBarWithText() {
  SearchBar(
    query = "B",
    onQueryChange = {},
    onNavigateBack = {}
  )
}