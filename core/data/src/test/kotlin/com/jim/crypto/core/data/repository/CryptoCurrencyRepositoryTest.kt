package com.jim.crypto.core.data.repository

import app.cash.turbine.test
import com.jim.crypto.core.data.model.asExternalModel
import com.jim.crypto.core.database.dao.CryptoCurrencyDao
import com.jim.crypto.core.database.model.CryptoCurrencyEntity
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class CryptoCurrencyRepositoryTest {

  private lateinit var repository: CryptoCurrencyRepository
  private val dao: CryptoCurrencyDao = mockk()

  @Before
  fun setup() {
    repository = CryptoCurrencyRepository(dao)
  }

  @Test
  fun `getItems returns list of CurrencyInfo`() = runTest {
    val cryptoEntities = listOf(CryptoCurrencyEntity("BTC", "Bitcoin", "BTC"))
    val expectedCurrencyInfos = cryptoEntities.map { it.asExternalModel() }

    coEvery { dao.getItems() } returns flowOf(cryptoEntities)

    repository.getItems().test {
      val result = awaitItem()
      assertEquals(expectedCurrencyInfos, result)
      cancelAndIgnoreRemainingEvents()
    }
  }

  @Test
  fun `getItems with query returns filtered list of CurrencyInfo`() = runTest {
    val cryptoEntities = listOf(
      CryptoCurrencyEntity("BTC", "Bitcoin", "BTC"),
      CryptoCurrencyEntity("ETH", "Ethereum", "ETH")
    )

    coEvery { dao.getItems("BTC") } returns flowOf(listOf(cryptoEntities[0]))

    repository.getItems("BTC").test {
      val result = awaitItem()
      assertEquals(listOf(cryptoEntities[0].asExternalModel()), result)
      cancelAndIgnoreRemainingEvents()
    }
  }

  @Test
  fun `getItemsSync returns list of CurrencyInfo`() = runTest {
    val cryptoEntities = listOf(CryptoCurrencyEntity("BTC", "Bitcoin", "BTC"))
    val expectedCurrencyInfos = cryptoEntities.map { it.asExternalModel() }

    coEvery { dao.getItemsSync() } returns cryptoEntities

    val result = repository.getItemsSync()
    assertEquals(expectedCurrencyInfos, result)
  }
}
