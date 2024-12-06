package com.jim.crypto.core.ui.currencylist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.jim.crypto.core.ui.R

@Composable
fun EmptySearchResultView() {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .fillMaxHeight(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Icon(
      imageVector = ImageVector.vectorResource(R.drawable.ic_not_found),
      contentDescription = "No results",
    )
    Text(
      text = stringResource(R.string.not_found_line_1),
      style = typography.bodyLarge,
      color = Color.Black
    )
    Text(
      text = stringResource(R.string.not_found_line_2),
      style = typography.bodyLarge,
      color = Color.DarkGray
    )
  }
}

@Preview(showBackground = true)
@Composable
fun PreviewEmptySearchResultView(){
  EmptySearchResultView()
}