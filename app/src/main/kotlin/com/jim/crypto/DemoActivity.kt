package com.jim.crypto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jim.crypto.core.ui.currencylist.CurrencyListScreen
import com.jim.crypto.core.ui.di.ViewModelModifier.CryptoViewModel
import com.jim.crypto.core.ui.di.ViewModelModifier.FiatViewModel
import com.jim.crypto.core.ui.di.ViewModelModifier.MixedViewModel
import com.jim.crypto.core.ui.theme.Dimens
import com.jim.crypto.ui.theme.CryptoTheme
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DemoActivity : ComponentActivity() {

  private val demoViewModel: DemoViewModel by viewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CryptoTheme {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "demo") {
          composable("demo") { DemoScreen(navController, demoViewModel) }
          composable("CryptoCurrencyList") { CurrencyListScreen(getViewModel(CryptoViewModel)) }
          composable("FiatCurrencyList") { CurrencyListScreen(getViewModel(FiatViewModel)) }
          composable("MixedCurrencyList") { CurrencyListScreen(getViewModel(MixedViewModel)) }
        }
      }
    }
  }
}

@Composable
fun DemoScreen(navController: NavController, viewModel: DemoViewModel) {
  val context = LocalContext.current
  val snackbarMessage by viewModel.snackbarMessage.collectAsState()
  val snackbarHostState = SnackbarHostState()

  LaunchedEffect(snackbarMessage) {
    snackbarMessage?.let {
      snackbarHostState.showSnackbar(it)
    }
  }

  Scaffold(
    snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
  ) { padding ->
    Column(
      modifier = Modifier
        .padding(padding)
        .fillMaxWidth()
        .fillMaxHeight(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.spacedBy(Dimens.Medium, Alignment.CenterVertically),
    ) {
      Button(onClick = {
        viewModel.onDataDelete(context.getString(R.string.data_cleared))
      }) {
        Text(stringResource(R.string.clear_database))
      }
      Button(onClick = {
        viewModel.onDataInsert(context.getString(R.string.data_inserted))
      }) {
        Text(stringResource(R.string.insert_data))
      }
      Button(onClick = { navController.navigate("CryptoCurrencyList") }) {
        Text(stringResource(R.string.open_crypto_currency_list))
      }
      Button(onClick = { navController.navigate("FiatCurrencyList") }) {
        Text(stringResource(R.string.open_fiat_currency_list))
      }
      Button(onClick = { navController.navigate("MixedCurrencyList") }) {
        Text(stringResource(R.string.open_mixed_currency_list))
      }
    }
  }
}