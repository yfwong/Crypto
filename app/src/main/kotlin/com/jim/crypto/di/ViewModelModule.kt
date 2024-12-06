package com.jim.crypto.di

import com.jim.crypto.ui.currencylist.CurrencyListViewModel
import com.jim.crypto.DemoViewModel
import com.jim.crypto.core.data.di.RepositoryQualifier.*
import com.jim.crypto.core.domain.di.UseCaseQualifier.*
import com.jim.crypto.di.ViewModelModifier.*
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.QualifierValue
import org.koin.dsl.module

sealed interface ViewModelModifier {
  object CryptoViewModel : Qualifier {
    override val value: QualifierValue = "crypto"
  }

  object FiatViewModel : Qualifier {
    override val value: QualifierValue = "fiat"
  }

  object MixedViewModel : Qualifier {
    override val value: QualifierValue = "mixed"
  }
}

val viewModelModule = module {
  viewModel { DemoViewModel(get(CryptoRepo), get(FiatRepo), get()) }

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
      get(SearchMixedUseCase)
    )
  }
}