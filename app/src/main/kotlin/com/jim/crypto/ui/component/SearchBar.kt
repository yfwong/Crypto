package com.jim.crypto.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.jim.crypto.ui.theme.AppSpacing

@Composable
fun SearchBar(
  searchQuery: TextFieldValue,
  onQueryChange: (TextFieldValue) -> Unit,
  onNavigateBack: () -> Unit
) {
  val focusRequester = remember { FocusRequester() }

  Row(
    modifier = Modifier
      .fillMaxWidth()
      .background(Color.LightGray.copy(alpha = 0.2f)),
    horizontalArrangement = Arrangement.Start,
    verticalAlignment = Alignment.CenterVertically
  ) {
    IconButton(onClick = { onNavigateBack() }) {
      Icon(
        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
        contentDescription = "Back"
      )
    }
    BasicTextField(
      value = searchQuery,
      onValueChange = onQueryChange,
      modifier = Modifier
        .weight(1f)
        .focusRequester(focusRequester),
      textStyle = typography.bodyLarge,
      decorationBox = { innerTextField ->
        Row(modifier = Modifier.padding(AppSpacing.Small)) {
          innerTextField()
        }
      }
    )
    if (searchQuery.text.isNotEmpty()) {
      IconButton(onClick = { onQueryChange(TextFieldValue("")) }) {
        Icon(
          imageVector = Icons.Filled.Clear,
          contentDescription = "Clear"
        )
      }
    }
  }

  // Automatically request focus when the composable is composed
  LaunchedEffect(Unit) {
    focusRequester.requestFocus()
  }
}

@Preview(showBackground = true)
@Composable
fun PreviewSearchBar() {
  SearchBar(
    searchQuery = TextFieldValue("B"),
    onQueryChange = {},
    onNavigateBack = {}
  )
}