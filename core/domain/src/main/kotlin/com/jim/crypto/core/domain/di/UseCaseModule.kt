package com.jim.crypto.core.domain.di

import com.jim.crypto.core.data.di.RepositoryQualifier.CombinedRepo
import com.jim.crypto.core.data.di.RepositoryQualifier.CryptoRepo
import com.jim.crypto.core.data.di.RepositoryQualifier.FiatRepo
import com.jim.crypto.core.domain.di.UseCaseQualifier.SearchCryptoUseCase
import com.jim.crypto.core.domain.di.UseCaseQualifier.SearchFiatUseCase
import com.jim.crypto.core.domain.di.UseCaseQualifier.SearchCombinedUseCase
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

  object SearchCombinedUseCase : Qualifier {
    override val value = "mixed.search"
  }
}

val domainModule = module {
  single(SearchCryptoUseCase) {
    SearchCurrencyListUseCase(get(CryptoRepo))
  }
  single(SearchFiatUseCase) {
    SearchCurrencyListUseCase(get(FiatRepo))
  }
  single(SearchCombinedUseCase) {
    SearchCurrencyListUseCase(get(CombinedRepo))
  }
}