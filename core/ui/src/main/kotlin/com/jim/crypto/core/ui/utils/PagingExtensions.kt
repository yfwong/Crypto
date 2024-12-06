package com.jim.crypto.core.ui.utils

import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

/**
 * Extension function to check if LazyPagingItems has empty data.
 */
fun <T : Any> LazyPagingItems<T>.isEmpty(): Boolean =
  itemCount == 0 &&
      loadState.refresh is LoadState.NotLoading &&
      loadState.append.endOfPaginationReached

/**
 * Extension function to check if the pagination has reached the end of the data.
 */
fun <T : Any> LazyPagingItems<T>.isEndOfData(): Boolean {
  return loadState.append is LoadState.NotLoading &&
      loadState.append.endOfPaginationReached
}

/**
 * Extension function to check if the data is currently loading.
 */
fun <T : Any> LazyPagingItems<T>.isLoading(): Boolean {
  return loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading
}