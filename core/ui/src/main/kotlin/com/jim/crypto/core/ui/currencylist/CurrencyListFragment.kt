package com.jim.crypto.core.ui.currencylist

import android.os.Bundle
import android.view.View
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.jim.crypto.core.model.data.CurrencyInfo
import com.jim.crypto.core.ui.R
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurrencyListFragment : Fragment(R.layout.fragment_empty) {

  private val viewModel: CurrencyListViewModel by viewModel()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val jsonString = requireArguments().getString(ARG_CURRENCIES)
    val currencies = jsonString?.let {
      Json.decodeFromString<List<CurrencyInfo>>(it)
    } ?: emptyList()

    view.findViewById<ComposeView>(R.id.compose_view).setContent {
      CurrencyListScreen(currencies, viewModel)
    }
  }

  companion object {
    const val ARG_CURRENCIES = "currencies"

    fun newInstance(currencies: ArrayList<CurrencyInfo>): CurrencyListFragment {
      return CurrencyListFragment().apply {
        val jsonString = Json.encodeToString(
          ListSerializer(CurrencyInfo.serializer()),
          currencies
        )
        arguments = Bundle().apply {
          putString(ARG_CURRENCIES, jsonString)
        }
      }
    }
  }
}
