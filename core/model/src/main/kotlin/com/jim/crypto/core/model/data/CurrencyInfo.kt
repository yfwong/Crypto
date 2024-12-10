package com.jim.crypto.core.model.data

import kotlinx.serialization.Serializable

/**
 * External model for currency, which can be either a cryptocurrency or a fiat currency.
 */
@Serializable
data class CurrencyInfo(
  val id: String,
  val name: String,
  val symbol: String,
  val code: String? = null
)