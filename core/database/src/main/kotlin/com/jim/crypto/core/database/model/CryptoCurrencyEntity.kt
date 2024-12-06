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
  @PrimaryKey val id: String,
  @ColumnInfo("name")
  val name: String,
  @ColumnInfo("symbol")
  val symbol: String
)
