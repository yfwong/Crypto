package com.jim.crypto.core.ui.currencylist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.jim.crypto.core.ui.R
import com.jim.crypto.core.ui.theme.Dimens

@Composable
fun EndOfDataFooterView() {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .padding(Dimens.Large),
    contentAlignment = Alignment.Center
  ) {
    Text(text = stringResource(R.string.the_end))
  }
}