package com.github.mhelmi.saryflagship.ui.store.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.mhelmi.saryflagship.core.state.UiState
import com.github.mhelmi.saryflagship.di.annotations.IoScheduler
import com.github.mhelmi.saryflagship.di.annotations.MainScheduler
import com.github.mhelmi.saryflagship.domain.store.models.Banner
import com.github.mhelmi.saryflagship.domain.store.models.Catalog
import com.github.mhelmi.saryflagship.domain.store.usecase.GetBannersUseCase
import com.github.mhelmi.saryflagship.domain.store.usecase.GetCatalogUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
  private val getBannersUseCase: GetBannersUseCase,
  private val getCatalogUseCase: GetCatalogUseCase,
  @IoScheduler private val ioScheduler: Scheduler,
  @MainScheduler private val mainScheduler: Scheduler
) : ViewModel() {

  private val disposables = CompositeDisposable()

  private val _bannersSliderState = MutableLiveData<UiState<List<Banner>>>()
  val bannersSliderState: LiveData<UiState<List<Banner>>> = _bannersSliderState

  private val _catalogsState = MutableLiveData<UiState<List<Catalog>>>()
  val catalogsState: LiveData<UiState<List<Catalog>>> = _catalogsState

  fun loadBanners() {
    disposables.add(
      getBannersUseCase()
        .doOnSubscribe { _bannersSliderState.postValue(UiState.Loading()) }
        .subscribeOn(ioScheduler)
        .observeOn(mainScheduler)
        .subscribe({
          _bannersSliderState.value =
            if (it.isNullOrEmpty()) UiState.Empty() else UiState.Success(it)
        }) {
          _bannersSliderState.value = UiState.Error()
        }
    )
  }

  fun loadCatalogs() {
    disposables.add(
      getCatalogUseCase()
        .doOnSubscribe {
          _catalogsState.postValue(UiState.Loading())
        }
        .subscribeOn(ioScheduler)
        .observeOn(mainScheduler)
        .subscribe({
          _catalogsState.value = if (it.isNullOrEmpty()) UiState.Empty() else UiState.Success(it)
        }) {
          _catalogsState.value = UiState.Error()
        }
    )
  }

  override fun onCleared() {
    super.onCleared()
    disposables.dispose()
  }
}