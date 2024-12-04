package com.jim.crypto.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.jim.crypto.ui.theme.AppSpacing

@Composable
fun LetterBadge(letter: String) {
  Box(contentAlignment = Alignment.Center) {
    Badge(
      modifier = Modifier,
      containerColor = Color.Gray,
      contentColor = Color.White,
    ) {
      Text(
        text = letter,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(AppSpacing.Small),
        color = Color.White
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
fun PreviewLetterBadge() {
  LetterBadge(letter = "A")
}
