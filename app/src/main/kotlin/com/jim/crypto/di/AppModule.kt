package com.jim.crypto.di

import com.jim.crypto.ui.currencylist.CurrencyListViewModel
import com.jim.crypto.DemoViewModel
import com.jim.crypto.core.data.di.RepositoryQualifier
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.QualifierValue
import org.koin.dsl.module

sealed class ViewModelModifier {
  object Crypto : Qualifier {
    override val value: QualifierValue = "crypto"
  }

  object Fiat : Qualifier {
    override val value: QualifierValue = "fiat"
  }

  object Mixed : Qualifier {
    override val value: QualifierValue = "mixed"
  }
}

val appModule = module {
  viewModel { DemoViewModel(get(RepositoryQualifier.Crypto), get(RepositoryQualifier.Fiat), get()) }
  viewModel(ViewModelModifier.Crypto) { CurrencyListViewModel(get(RepositoryQualifier.Crypto)) }
  viewModel(ViewModelModifier.Fiat) { CurrencyListViewModel(get(RepositoryQualifier.Fiat)) }
  viewModel(ViewModelModifier.Mixed) { CurrencyListViewModel(get(RepositoryQualifier.Fiat)) } // TODO
}