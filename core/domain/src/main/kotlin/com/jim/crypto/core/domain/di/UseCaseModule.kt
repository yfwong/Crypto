package com.jim.crypto.core.domain.di

import com.jim.crypto.core.data.di.RepositoryQualifier
import com.jim.crypto.core.data.di.RepositoryQualifier.*
import com.jim.crypto.core.domain.di.UseCaseQualifier.GetCryptoUseCase
import com.jim.crypto.core.domain.di.UseCaseQualifier.GetFiatUseCase
import com.jim.crypto.core.domain.di.UseCaseQualifier.GetMixedUseCase
import com.jim.crypto.core.domain.di.UseCaseQualifier.SearchCryptoUseCase
import com.jim.crypto.core.domain.di.UseCaseQualifier.SearchFiatUseCase
import com.jim.crypto.core.domain.di.UseCaseQualifier.SearchMixedUseCase
import com.jim.crypto.core.domain.usecase.GetCurrencyListUseCase
import com.jim.crypto.core.domain.usecase.SearchCurrencyListUseCase
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.module

sealed interface UseCaseQualifier {
  object GetCryptoUseCase : Qualifier {
    override val value = "crypto.get"
  }

  object GetFiatUseCase : Qualifier {
    override val value = "fiat.get"
  }

  object GetMixedUseCase : Qualifier {
    override val value = "mixed.get"
  }

  object SearchCryptoUseCase : Qualifier {
    override val value = "crypto.search"
  }

  object SearchFiatUseCase : Qualifier {
    override val value = "fiat.search"
  }

  object SearchMixedUseCase : Qualifier {
    override val value = "mixed.search"
  }
}

val useCaseModule = module {
  single(GetCryptoUseCase) {
    GetCurrencyListUseCase(get(CryptoRepo))
  }
  single(SearchCryptoUseCase) {
    SearchCurrencyListUseCase(get(CryptoRepo))
  }

  single(GetFiatUseCase) {
    GetCurrencyListUseCase(get(FiatRepo))
  }
  single(SearchFiatUseCase) {
    SearchCurrencyListUseCase(get(FiatRepo))
  }

  single(GetMixedUseCase) {
    GetCurrencyListUseCase(get(CombinedRepo))
  }
  single(SearchMixedUseCase) {
    SearchCurrencyListUseCase(get(CombinedRepo))
  }
}