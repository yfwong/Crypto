package com.jim.crypto.core.ui.di

import com.jim.crypto.core.domain.di.UseCaseQualifier.SearchCombinedUseCase
import com.jim.crypto.core.domain.di.UseCaseQualifier.SearchCryptoUseCase
import com.jim.crypto.core.domain.di.UseCaseQualifier.SearchFiatUseCase
import com.jim.crypto.core.ui.currencylist.CurrencyListViewModel
import com.jim.crypto.core.ui.di.ViewModelModifier.CryptoViewModel
import com.jim.crypto.core.ui.di.ViewModelModifier.FiatViewModel
import com.jim.crypto.core.ui.di.ViewModelModifier.MixedViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.module

sealed interface ViewModelModifier {
  object CryptoViewModel : Qualifier {
    override val value = "crypto"
  }

  object FiatViewModel : Qualifier {
    override val value = "fiat"
  }

  object MixedViewModel : Qualifier {
    override val value = "mixed"
  }
}

val uiModule = module {
  viewModel(CryptoViewModel) {
    CurrencyListViewModel(
      get(SearchCryptoUseCase)
    )
  }
  viewModel(FiatViewModel) {
    CurrencyListViewModel(
      get(SearchFiatUseCase)
    )
  }
  viewModel(MixedViewModel) {
    CurrencyListViewModel(
      get(SearchCombinedUseCase)
    )
  }
}