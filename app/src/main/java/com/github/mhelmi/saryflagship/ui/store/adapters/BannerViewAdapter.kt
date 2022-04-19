package com.github.mhelmi.saryflagship.ui.store.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.mhelmi.saryflagship.core.extesions.load
import com.github.mhelmi.saryflagship.core.extesions.toast
import com.github.mhelmi.saryflagship.databinding.ItemBannerBinding
import com.github.mhelmi.saryflagship.domain.store.models.CatalogItem

class BannersAdapter :
  ListAdapter<CatalogItem, BannersAdapter.BannerViewHolder>(CatalogItem.DIFF_CALLBACK) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
    return BannerViewHolder(
      ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
  }

  override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
    holder.bindItem(getItem(position))
  }

  inner class BannerViewHolder(private val binding: ItemBannerBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindItem(item: CatalogItem) = binding.apply {
      itemView.setOnClickListener {
        item.name?.let { root.context.toast(it) }
      }
      ivBanner.load(item.imageUrl)
    }
  }
}