package com.jim.crypto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jim.crypto.ui.theme.CryptoTheme

/**
 * The DemoActivity should provide two datasets, Currency List A and Currency List B,
 * which contain CurrencyInfo objects to be queried from the local database.
 *
 * The DemoActivity should also include five buttons for demonstrating various
 * functionalities:
 * ○ The first button is responsible for clearing the data in the local database.
 * ○ The second button is used to insert the data into the local database.
 * ○ The third button changes the CurrencyListFragment to use Currency List A -
 * Crypto.
 * ○ The fourth button changes the CurrencyListFragment to use Currency List B -
 * Fiat.
 * ○ The fifth button displays all CurrencyInfo objects that can be purchased from
 * Currency List A and B.
 */
class DemoActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      CryptoTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          Greeting(
            name = "Jim",
            modifier = Modifier.padding(innerPadding)
          )
        }
      }
    }
  }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
  Text(
    text = "Hello $name!",
    modifier = modifier
  )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  CryptoTheme {
    Greeting("Android")
  }
}