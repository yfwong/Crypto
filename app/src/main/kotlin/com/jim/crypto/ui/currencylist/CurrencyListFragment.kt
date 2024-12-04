package com.jim.crypto.ui.currencylist

/**
 * The CurrencyListFragment is expected to receive an ArrayList of CurrencyInfo
 * objects to create the UI.
 *
 * Additionally, the CurrencyListFragment should provide a search feature that can be
 * cancelled when the user clicks the back or close button.
 *
 * Furthermore, the CurrencyListFragment should include an empty view for displaying
 * an empty list.
 */
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment

class CurrencyListFragment : Fragment() {
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return ComposeView(requireContext()).apply {
      setContent {
        // CurrencyListScreen()
      }
    }
  }
}