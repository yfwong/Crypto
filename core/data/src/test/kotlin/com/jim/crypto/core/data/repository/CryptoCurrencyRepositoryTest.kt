package com.jim.crypto.core.data.repository

import app.cash.turbine.test
import com.jim.crypto.core.data.model.asCryptoEntity
import com.jim.crypto.core.data.model.asExternalModel
import com.jim.crypto.core.database.dao.CryptoCurrencyDao
import com.jim.crypto.core.database.model.CryptoCurrencyEntity
import com.jim.crypto.core.model.data.CurrencyInfo
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class CryptoCurrencyRepositoryTest {

  private lateinit var repository: CryptoCurrencyRepository
  private val dao: CryptoCurrencyDao = mockk()
  private val dispatcher = StandardTestDispatcher()

  @Before
  fun setup() {
    Dispatchers.setMain(dispatcher)
    repository = CryptoCurrencyRepositoryImpl(dao, dispatcher)
  }

  @After
  fun tearDown() {
    Dispatchers.resetMain()
  }

  @Test
  fun `getItems returns list of CurrencyInfo`() = runTest {
    val cryptoEntities = listOf(CryptoCurrencyEntity("BTC", "Bitcoin", "BTC"))
    val expectedCurrencyInfos = cryptoEntities.map { it.asExternalModel() }

    coEvery { dao.getItems() } returns flowOf(cryptoEntities)

    repository.getItems().test {
      val result = awaitItem()
      assertEquals(expectedCurrencyInfos, result)
      awaitComplete()
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
      awaitComplete()
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

  @Test
  fun `test insertItems calls dao insert`() = runTest {
    val currenciesToInsert = listOf(
      CurrencyInfo("BTC", "Bitcoin", "BTC"),
      CurrencyInfo("ETH", "Ethereum", "ETH")
    )

    coEvery { dao.insertItems(any()) } just Runs

    // Call the insertItems method
    repository.inertItems(currenciesToInsert)

    // Verify that the insert function of DAO was called
    coVerify { dao.insertItems(currenciesToInsert.map { it.asCryptoEntity() }) }
  }

  @Test
  fun `test deleteItems calls dao delete`() = runTest {
    coEvery { dao.deleteItems() } just Runs

    // Call the deleteItems method
    repository.deleteItems()

    // Verify that the delete function of DAO was called
    coVerify { dao.deleteItems() }
  }
}
