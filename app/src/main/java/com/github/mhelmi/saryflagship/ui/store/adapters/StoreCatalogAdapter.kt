package com.github.mhelmi.saryflagship.ui.store.adapters

import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.mhelmi.saryflagship.core.extesions.layoutInflater
import com.github.mhelmi.saryflagship.databinding.ItemBannerSectionBinding
import com.github.mhelmi.saryflagship.databinding.ItemGroupSectionBinding
import com.github.mhelmi.saryflagship.databinding.ItemSmartSectionBinding
import com.github.mhelmi.saryflagship.domain.store.annotations.CatalogDataType
import com.github.mhelmi.saryflagship.domain.store.annotations.CatalogUiType
import com.github.mhelmi.saryflagship.domain.store.models.Catalog

class StoreCatalogAdapter : ListAdapter<Catalog, RecyclerView.ViewHolder>(Catalog.DIFF_CALLBACK) {

  override fun getItemViewType(position: Int): Int {
    val item = getItem(position)
    return when (item.dataType) {
      CatalogDataType.BANNER -> BANNER_ITEM
      CatalogDataType.GROUP -> GROUP_ITEM
      else -> SMART_ITEM
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    return when (viewType) {
      BANNER_ITEM -> BannerSectionViewHolder(
        ItemBannerSectionBinding.inflate(parent.layoutInflater, parent, false)
      )
      GROUP_ITEM -> GroupSectionViewHolder(
        ItemGroupSectionBinding.inflate(parent.layoutInflater, parent, false)
      )
      else -> SmartSectionViewHolder(
        ItemSmartSectionBinding.inflate(parent.layoutInflater, parent, false)
      )
    }
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    val item = getItem(position)
    when (holder) {
      is BannerSectionViewHolder -> holder.bindItem(item)
      is GroupSectionViewHolder -> holder.bindItem(item)
      is SmartSectionViewHolder -> holder.bindItem(item)
    }
  }

  inner class BannerSectionViewHolder(private val binding: ItemBannerSectionBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindItem(item: Catalog) = binding.apply {
      rvBanners.apply {
        setHasFixedSize(true)
        adapter = BannersAdapter().also { it.submitList(item.catalogItems) }
      }
    }
  }

  inner class GroupSectionViewHolder(private val binding: ItemGroupSectionBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindItem(item: Catalog) = binding.apply {
      tvTitle.text = item.title
      tvTitle.isVisible = item.showTitle
      rvGroupItems.apply {
        setHasFixedSize(true)
        layoutManager = if (item.uiType == CatalogUiType.GRID) GridLayoutManager(
          binding.root.context,
          item.rowCount
        )
        else LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        adapter = GroupViewAdapter().also { it.submitList(item.notNullCatalogItems) }
      }
    }
  }

  inner class SmartSectionViewHolder(private val binding: ItemSmartSectionBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindItem(item: Catalog) = binding.apply {
      tvSectionTitle.text = item.title
      tvSectionTitle.isVisible = item.showTitle

      rvSmartItems.apply {
        setHasFixedSize(true)
        adapter = SmartViewAdapter().also { it.submitList(item.catalogItems) }
      }
    }
  }

  companion object {
    const val BANNER_ITEM = 1
    const val GROUP_ITEM = 2
    const val SMART_ITEM = 3
  }
}