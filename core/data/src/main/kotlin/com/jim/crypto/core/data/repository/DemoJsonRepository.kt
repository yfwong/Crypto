package com.jim.crypto.core.data.repository

import android.content.Context
import androidx.annotation.RawRes
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jim.crypto.core.data.R
import com.jim.crypto.core.model.data.CurrencyInfo
import java.io.InputStreamReader

class DemoJsonRepository(private val context: Context) {

  suspend fun getCryptoDataFromJson(): List<CurrencyInfo> =
    getObjectsFromJson(context, R.raw.crypto_data)

  suspend fun getFiatDataFromJson(): List<CurrencyInfo> =
    getObjectsFromJson(context, R.raw.fiat_data)

  private fun getObjectsFromJson(context: Context, @RawRes id: Int): List<CurrencyInfo> {
    // Open the raw resource stream
    val inputStream = context.resources.openRawResource(id)
    val reader = InputStreamReader(inputStream)

    // Use Gson to parse the JSON
    val type = object : TypeToken<List<CurrencyInfo>>() {}.type
    val objects: List<CurrencyInfo> = Gson().fromJson(reader, type)

    reader.close() // Close the reader
    return objects
  }
}