package com.jim.crypto.core.data.repository

import app.cash.turbine.test
import com.jim.crypto.core.data.model.asExternalModel
import com.jim.crypto.core.data.model.asFiatEntity
import com.jim.crypto.core.database.dao.FiatCurrencyDao
import com.jim.crypto.core.database.model.FiatCurrencyEntity
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
class FiatCurrencyRepositoryTest {

  private lateinit var repository: FiatCurrencyRepository
  private val dao: FiatCurrencyDao = mockk()
  private val dispatcher = StandardTestDispatcher()

  @Before
  fun setup() {
    Dispatchers.setMain(dispatcher)
    repository = FiatCurrencyRepository(dao, dispatcher)
  }

  @After
  fun tearDown() {
    Dispatchers.resetMain()
  }

  @Test
  fun `getItems returns list of CurrencyInfo`() = runTest {
    val fiatEntities = listOf(FiatCurrencyEntity("HKD", "HK Dollar", "$", "HKD"))
    val expectedCurrencyInfos = fiatEntities.map { it.asExternalModel() }

    coEvery { dao.getItems() } returns flowOf(fiatEntities)

    repository.getItems().test {
      val result = awaitItem()
      assertEquals(expectedCurrencyInfos, result)
      awaitComplete()
    }
  }

  @Test
  fun `getItems with query returns filtered list of CurrencyInfo`() = runTest {
    val fiatEntities = listOf(
      FiatCurrencyEntity("HKD", "HK Dollar", "$", "HKD"),
      FiatCurrencyEntity("USD", "US Dollar", "$", "USD")
    )

    coEvery { dao.getItems("HKD") } returns flowOf(listOf(fiatEntities[0]))

    repository.getItems("HKD").test {
      val result = awaitItem()
      assertEquals(listOf(fiatEntities[0].asExternalModel()), result)
      awaitComplete()
    }
  }

  @Test
  fun `getItemsSync returns list of CurrencyInfo`() = runTest {
    val fiatEntities = listOf(FiatCurrencyEntity("HKD", "HK Dollar", "$", "HKD"))
    val expectedCurrencyInfos = fiatEntities.map { it.asExternalModel() }

    coEvery { dao.getItemsSync() } returns fiatEntities

    val result = repository.getItemsSync()
    assertEquals(expectedCurrencyInfos, result)
  }

  @Test
  fun `test insertItems calls dao insert`() = runTest {
    val currenciesToInsert = listOf(
      CurrencyInfo("HKD", "HK Dollar", "$", "HKD"),
      CurrencyInfo("USD", "US Dollar", "$", "USD")
    )

    coEvery { dao.insertItems(any()) } just Runs

    // Call the insertItems method
    repository.inertItems(currenciesToInsert)

    // Verify that the insert function of DAO was called
    coVerify { dao.insertItems(currenciesToInsert.map { it.asFiatEntity() }) }
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
