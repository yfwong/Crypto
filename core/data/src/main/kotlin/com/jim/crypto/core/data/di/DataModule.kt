package com.jim.crypto.core.data.di

import com.jim.crypto.core.data.repository.CurrencyRepository
import com.jim.crypto.core.data.repository.CryptoCurrencyRepository
import com.jim.crypto.core.data.repository.FiatCurrencyRepository
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.QualifierValue
import org.koin.dsl.module

object CryptoQualifier : Qualifier {
  override val value: QualifierValue = "core.data.repository.crypto"
}

object FiatQualifier : Qualifier {
  override val value: QualifierValue = "core.data.repository.fiat"
}

val dataModule = module {
  single<CurrencyRepository>(CryptoQualifier) { CryptoCurrencyRepository(get()) }
  single<CurrencyRepository>(FiatQualifier) { FiatCurrencyRepository(get()) }
}