package com.jim.crypto.core.database.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class CombinedCurrencyEntity(
  @PrimaryKey override val id: String,
  @ColumnInfo("name")
  override val name: String,
  @ColumnInfo("symbol")
  override val symbol: String,
  @ColumnInfo("code")
  val code: String? = null
) : CurrencyEntity
