package com.jim.crypto.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
  tableName = "crypto_currency",
  indices = [Index(value = ["name"]), Index(value = ["symbol"])]
)
data class CryptoCurrencyEntity(
  @PrimaryKey override val id: String,
  @ColumnInfo("name")
  override val name: String,
  @ColumnInfo("symbol")
  override val symbol: String
) : CurrencyEntity
