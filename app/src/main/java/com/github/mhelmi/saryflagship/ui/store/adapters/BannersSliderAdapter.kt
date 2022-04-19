package com.github.mhelmi.saryflagship.ui.store.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.mhelmi.saryflagship.core.extesions.load
import com.github.mhelmi.saryflagship.core.extesions.toast
import com.github.mhelmi.saryflagship.databinding.ItemBannerSliderBinding
import com.github.mhelmi.saryflagship.domain.store.models.Banner

class BannersSliderAdapter :
  ListAdapter<Banner, BannersSliderAdapter.BannerSliderViewHolder>(Banner.DIFF_CALLBACK) {

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): BannerSliderViewHolder {
    return BannerSliderViewHolder(
      ItemBannerSliderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
  }

  override fun onBindViewHolder(holder: BannerSliderViewHolder, position: Int) {
    holder.bindItem(getItem(position))
  }

  inner class BannerSliderViewHolder(private val binding: ItemBannerSliderBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindItem(item: Banner) = binding.apply {
      itemView.setOnClickListener {
        item.imageUrl?.let { root.context.toast(it) }
      }
      ivBannerImage.load(item.imageUrl)
    }
  }
}