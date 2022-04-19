package com.github.mhelmi.saryflagship.ui.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.mhelmi.saryflagship.core.state.UiState
import com.github.mhelmi.saryflagship.databinding.FragmentStoreBinding
import com.github.mhelmi.saryflagship.domain.store.models.Banner
import com.github.mhelmi.saryflagship.domain.store.models.Catalog
import com.github.mhelmi.saryflagship.ui.store.adapters.BannersSliderAdapter
import com.github.mhelmi.saryflagship.ui.store.adapters.StoreCatalogAdapter
import com.github.mhelmi.saryflagship.ui.store.viewmodel.StoreViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoreFragment : Fragment() {
  private var binding: FragmentStoreBinding? = null

  private val storeViewModel: StoreViewModel by viewModels()

  private lateinit var bannersSliderAdapter: BannersSliderAdapter
  private lateinit var storeCatalogAdapter: StoreCatalogAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    storeViewModel.loadBanners()
    storeViewModel.loadCatalogs()
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = FragmentStoreBinding.inflate(inflater, container, false)
    return binding?.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupViewPager()
    setupCatalogRecyclerView()
    observeData()
  }

  private fun setupViewPager() = binding?.apply {
    bannersSliderAdapter = BannersSliderAdapter()
    viewPagerBanners.adapter = bannersSliderAdapter
  }

  private fun setupCatalogRecyclerView() = binding?.rvCatalog?.apply {
    setHasFixedSize(true)
    storeCatalogAdapter = StoreCatalogAdapter()
    adapter = storeCatalogAdapter
  }

  private fun observeData() {
    storeViewModel.bannersSliderState.observe(viewLifecycleOwner, ::handleBannersSliderState)
    storeViewModel.catalogsState.observe(viewLifecycleOwner, ::handleCatalogsState)
  }

  private fun handleBannersSliderState(state: UiState<List<Banner>>) = binding?.apply {
    progressBarSlider.isVisible = state is UiState.Loading
    viewPagerBanners.isVisible = state is UiState.Success
    dotsIndicator.isVisible = state is UiState.Success
    when (state) {
      is UiState.Success -> {
        bannersSliderAdapter.submitList(state.data)
        dotsIndicator.setViewPager2(viewPagerBanners)
      }
      else -> Unit
    }
  }

  private fun handleCatalogsState(state: UiState<List<Catalog>>) = binding?.apply {
    progressBarCatalog.isVisible = state is UiState.Loading
    tvError.isVisible = state is UiState.Error
    rvCatalog.isVisible = state is UiState.Success
    when (state) {
      is UiState.Success -> storeCatalogAdapter.submitList(state.data)
      else -> Unit
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    binding = null
  }
}