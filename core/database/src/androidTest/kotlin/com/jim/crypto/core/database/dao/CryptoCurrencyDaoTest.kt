package com.jim.crypto.core.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.jim.crypto.core.database.AppDatabase
import com.jim.crypto.core.database.model.CryptoCurrencyEntity
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class CryptoCurrencyDaoTest {

  private lateinit var database: AppDatabase
  private lateinit var dao: CryptoCurrencyDao

  @Before
  fun setup() {
    // Create an in-memory database for testing
    database = Room.inMemoryDatabaseBuilder(
      ApplicationProvider.getApplicationContext(),
      AppDatabase::class.java
    ).build()
    dao = database.cryptoCurrencyDao()
  }

  @After
  fun tearDown() {
    database.close()
  }

  @Test
  fun getItems_returns_list_of_CryptoCurrencyEntities() = runTest {
    // Given
    val cryptoEntities = listOf(
      CryptoCurrencyEntity("BTC", "Bitcoin", "BTC"),
      CryptoCurrencyEntity("ETH", "Ethereum", "ETH")
    )
    dao.insertItems(cryptoEntities)

    // When & Then
    dao.getItems().test {
      val result = awaitItem()
      assertEquals(cryptoEntities, result)
      cancelAndIgnoreRemainingEvents()
    }
  }

  @Test
  fun getItems_with_query_filters_results() = runTest {
    // Given
    val cryptoEntities = listOf(
      CryptoCurrencyEntity("BTC", "Bitcoin", "BTC"),
      CryptoCurrencyEntity("CRO", "Cronos", "CRO"),
      CryptoCurrencyEntity("ETH", "Ethereum", "ETH")
    )
    dao.insertItems(cryptoEntities)

    // When & Then
    dao.getItems("Bit").test {
      val result = awaitItem()
      assertEquals(listOf(cryptoEntities[0]), result) // Only Bitcoin should match
      cancelAndIgnoreRemainingEvents()
    }

    dao.getItems("CRO").test {
      val result = awaitItem()
      assertEquals(listOf(cryptoEntities[1]), result) // Only Ethereum should match
      cancelAndIgnoreRemainingEvents()
    }

    dao.getItems("X").test {
      val result = awaitItem()
      assertEquals(emptyList(), result) // No match for "X"
      cancelAndIgnoreRemainingEvents()
    }
  }

  @Test
  fun getItemsSync_returns_list_of_CryptoCurrencyEntities() = runTest {
    // Given
    val cryptoEntities = listOf(
      CryptoCurrencyEntity("BTC", "Bitcoin", "BTC"),
      CryptoCurrencyEntity("ETH", "Ethereum", "ETH")
    )
    dao.insertItems(cryptoEntities)

    // When
    val result = dao.getItemsSync()

    // Then
    assertEquals(cryptoEntities, result)
  }

  @Test
  fun insertItems_inserts_and_updates_entities() = runTest {
    // Given
    val cryptoEntities = listOf(
      CryptoCurrencyEntity("BTC", "Bitcoin", "BTC"),
      CryptoCurrencyEntity("ETH", "Ethereum", "ETH")
    )
    dao.insertItems(cryptoEntities)

    // When
    val updatedCryptoEntities = listOf(
      CryptoCurrencyEntity("BTC", "Bitcoin", "BTC"),
      CryptoCurrencyEntity("CRO", "Cronos", "CRO"),
      CryptoCurrencyEntity("ETH", "Ethereum", "ETH")
    )
    dao.insertItems(updatedCryptoEntities)

    // Then
    val result = dao.getItemsSync()
    assertEquals(updatedCryptoEntities, result) // Ensure the updated list is inserted
  }

  @Test
  fun deleteItems_removes_all_entities() = runTest {
    // Given
    val cryptoEntities = listOf(
      CryptoCurrencyEntity("BTC", "Bitcoin", "BTC"),
      CryptoCurrencyEntity("ETH", "Ethereum", "ETH")
    )
    dao.insertItems(cryptoEntities)

    // When
    dao.deleteItems()

    // Then
    val result = dao.getItemsSync()
    assertEquals(emptyList<CryptoCurrencyEntity>(), result) // Ensure the table is empty
  }
}
