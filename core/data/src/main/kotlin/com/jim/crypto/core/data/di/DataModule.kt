package com.jim.crypto.core.data.di

import com.jim.crypto.core.data.repository.CurrencyRepository
import com.jim.crypto.core.data.repository.CryptoCurrencyRepository
import com.jim.crypto.core.data.repository.DemoJsonRepository
import com.jim.crypto.core.data.repository.FiatCurrencyRepository
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.QualifierValue
import org.koin.dsl.module

sealed class RepositoryQualifier {
  object Crypto : Qualifier {
    override val value: QualifierValue = "crypto"
  }

  object Fiat : Qualifier {
    override val value: QualifierValue = "fiat"
  }
}

val dataModule = module {
  single<CurrencyRepository>(RepositoryQualifier.Crypto) { CryptoCurrencyRepository(get()) }
  single<CurrencyRepository>(RepositoryQualifier.Fiat) { FiatCurrencyRepository(get()) }
  single<DemoJsonRepository> { DemoJsonRepository(get()) }
}