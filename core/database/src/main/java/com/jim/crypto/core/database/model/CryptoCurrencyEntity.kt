package com.jim.crypto.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jim.crypto.core.model.CryptoCurrency

@Entity(tableName = "crypto_currency")
data class CryptoCurrencyEntity(
  @PrimaryKey val id: String,
  @ColumnInfo("name")
  val name: String,
  @ColumnInfo("symbol")
  val symbol: String
)

fun CryptoCurrencyEntity.asExternalModel() = CryptoCurrency(
  id = id,
  name = name,
  symbol = symbol
)
