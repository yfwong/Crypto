package com.jim.crypto.core.data.di

import com.jim.crypto.core.data.di.RepositoryQualifier.*
import com.jim.crypto.core.data.repository.CombinedCurrencyRepository
import com.jim.crypto.core.data.repository.CryptoCurrencyRepository
import com.jim.crypto.core.data.repository.CurrencyRepository
import com.jim.crypto.core.data.repository.DemoJsonRepository
import com.jim.crypto.core.data.repository.FiatCurrencyRepository
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.module

sealed interface RepositoryQualifier {
  object CryptoRepo : Qualifier {
    override val value = "crypto"
  }

  object FiatRepo : Qualifier {
    override val value = "fiat"
  }

  object CombinedRepo : Qualifier {
    override val value = "combined"
  }
}

val dataModule = module {
  single<CurrencyRepository>(CryptoRepo) { CryptoCurrencyRepository(get()) }
  single<CurrencyRepository>(FiatRepo) { FiatCurrencyRepository(get()) }
  single<CurrencyRepository>(CombinedRepo) { CombinedCurrencyRepository(get()) }
  single<DemoJsonRepository> { DemoJsonRepository(get()) }
}