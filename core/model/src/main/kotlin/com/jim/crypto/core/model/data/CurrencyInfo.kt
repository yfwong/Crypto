package com.jim.crypto.core.model.data

/**
 * Represents a currency, which can be either a cryptocurrency or a fiat currency.
 */
data class CurrencyInfo(
  val id: String,
  val name: String,
  val symbol: String,
  val code: String?
)