package com.jim.crypto.core.data.model

import com.jim.crypto.core.database.model.CryptoCurrencyEntity
import com.jim.crypto.core.model.data.CryptoCurrency

fun CryptoCurrencyEntity.asExternalModel() = CryptoCurrency(
  id = id,
  name = name,
  symbol = symbol
)

fun List<CryptoCurrencyEntity>.asExternalModel() = this.map {
  it.asExternalModel()
}

fun CryptoCurrency.asEntity() = CryptoCurrencyEntity(
  id = id,
  name = name,
  symbol = symbol
)

fun List<CryptoCurrency>.asEntity() = this.map {
  it.asEntity()
}
