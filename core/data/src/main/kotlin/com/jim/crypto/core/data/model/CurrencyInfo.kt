package com.jim.crypto.core.data.model

import com.jim.crypto.core.database.model.CombinedCurrencyEntity
import com.jim.crypto.core.database.model.CryptoCurrencyEntity
import com.jim.crypto.core.database.model.FiatCurrencyEntity
import com.jim.crypto.core.model.data.CurrencyInfo

// Mapping functions between DB entity and external model

fun CurrencyInfo.asCryptoEntity() = CryptoCurrencyEntity(
  id = id,
  name = name,
  symbol = symbol
)

fun CurrencyInfo.asFiatEntity() = FiatCurrencyEntity(
  id = id,
  name = name,
  symbol = symbol,
  code = code
)

fun FiatCurrencyEntity.asExternalModel() = CurrencyInfo(
  id = id,
  name = name,
  symbol = symbol,
  code = code
)

fun CryptoCurrencyEntity.asExternalModel() = CurrencyInfo(
  id = id,
  name = name,
  symbol = symbol
)

fun CombinedCurrencyEntity.asExternalModel() = CurrencyInfo(
  id = id,
  name = name,
  symbol = symbol,
  code = code
)