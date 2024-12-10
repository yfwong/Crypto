package com.jim.crypto.core.ui.currencylist

import android.os.Bundle
import android.view.View
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.jim.crypto.core.model.data.CurrencyInfo
import com.jim.crypto.core.ui.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurrencyListFragment : Fragment(R.layout.fragment_empty) {

  private val viewModel: CurrencyListViewModel by viewModel()

  // TODO
  val currencies = listOf(CurrencyInfo("BTC", "Bitcoin", "BTC"))

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    // Initialize any dependencies or setup as needed.
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    view.findViewById<ComposeView>(R.id.compose_view).setContent {
      CurrencyListScreen(currencies, viewModel)
    }
  }

  companion object {
    const val ARG_CURRENCIES = "currencies"

    fun newInstance(currencies: List<CurrencyInfo>): CurrencyListFragment {
      return CurrencyListFragment().apply {
//        arguments = Bundle().apply {
//          putParcelableArrayList(ARG_CURRENCIES, ArrayList(currencies))
//        }
      }
    }
  }
}
