package com.jim.crypto.core.ui.currencylist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jim.crypto.core.ui.theme.Dimens

@Composable
fun LoadingView() {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .padding(Dimens.Medium),
    contentAlignment = Alignment.Center
  ) {
    CircularProgressIndicator(modifier = Modifier.size(Dimens.LoadingProgressSize))
  }
}
