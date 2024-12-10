package com.jim.crypto.core.data.repository

import app.cash.turbine.test
import com.jim.crypto.core.data.model.asExternalModel
import com.jim.crypto.core.database.dao.FiatCurrencyDao
import com.jim.crypto.core.database.model.FiatCurrencyEntity
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class FiatCurrencyRepositoryTest {

  private lateinit var repository: FiatCurrencyRepository
  private val dao: FiatCurrencyDao = mockk()

  @Before
  fun setup() {
    repository = FiatCurrencyRepository(dao)
  }

  @Test
  fun `getItems returns list of CurrencyInfo`() = runTest {
    val fiatEntities = listOf(FiatCurrencyEntity("HKD", "HK Dollars", "$", "HKD"))
    val expectedCurrencyInfos = fiatEntities.map { it.asExternalModel() }

    coEvery { dao.getItems() } returns flowOf(fiatEntities)

    repository.getItems().test {
      val result = awaitItem()
      assertEquals(expectedCurrencyInfos, result)
      cancelAndIgnoreRemainingEvents()
    }
  }

  @Test
  fun `getItems with query returns filtered list of CurrencyInfo`() = runTest {
    val fiatEntities = listOf(
      FiatCurrencyEntity("BTC", "Bitcoin", "BTC"),
      FiatCurrencyEntity("ETH", "Ethereum", "ETH")
    )

    coEvery { dao.getItems("BTC") } returns flowOf(listOf(fiatEntities[0]))

    repository.getItems("BTC").test {
      val result = awaitItem()
      assertEquals(listOf(fiatEntities[0].asExternalModel()), result)
      cancelAndIgnoreRemainingEvents()
    }
  }

  @Test
  fun `getItemsSync returns list of CurrencyInfo`() = runTest {
    val fiatEntities = listOf(FiatCurrencyEntity("BTC", "Bitcoin", "BTC"))
    val expectedCurrencyInfos = fiatEntities.map { it.asExternalModel() }

    coEvery { dao.getItemsSync() } returns fiatEntities

    val result = repository.getItemsSync()
    assertEquals(expectedCurrencyInfos, result)
  }
}
