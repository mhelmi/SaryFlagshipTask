package com.github.mhelmi.saryflagship.core.state

import androidx.annotation.Keep
import com.github.mhelmi.saryflagship.R

/**
 * This class is used to identify and manage the UI different states base on the data loading states
 * like loading, error, and success
 * @param T it take an object of type T
 */
@Keep
sealed class UiState<T> {
  class Init<T> : UiState<T>()

  /**
   * For ex: UI will show normal loading progress bar.
   */
  class Loading<T> : UiState<T>()

  /**
   * Mainly used in endless scroller of the recycler view to show bottom loading item in the list.
   */
  class LoadMore<T> : UiState<T>()

  data class Success<T>(val data: T) : UiState<T>()

  data class Error<T>(val errorMessage: String = "") : UiState<T>()

  class Empty<T>: UiState<T>()
}