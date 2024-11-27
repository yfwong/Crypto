package com.jim.crypto.core.data.model

import com.jim.crypto.core.database.model.FiatCurrencyEntity
import com.jim.crypto.core.model.data.FiatCurrency

fun FiatCurrencyEntity.asExternalModel() = FiatCurrency(
  id = id,
  name = name,
  symbol = symbol,
  code = code
)

fun List<FiatCurrencyEntity>.asExternalModel() = this.map {
  it.asExternalModel()
}

fun FiatCurrency.asEntity() = FiatCurrencyEntity(
  id = id,
  name = name,
  symbol = symbol,
  code = code
)

fun List<FiatCurrency>.asEntity() = this.map {
  it.asEntity()
}
