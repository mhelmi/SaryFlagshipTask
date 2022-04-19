package com.github.mhelmi.saryflagship.ui.store.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.mhelmi.saryflagship.core.extesions.load
import com.github.mhelmi.saryflagship.core.extesions.toast
import com.github.mhelmi.saryflagship.databinding.ItemSmartBinding
import com.github.mhelmi.saryflagship.domain.store.models.CatalogItem

class SmartViewAdapter :
  ListAdapter<CatalogItem, SmartViewAdapter.SmartViewHolder>(CatalogItem.DIFF_CALLBACK) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmartViewHolder {
    return SmartViewHolder(
      ItemSmartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
  }

  override fun onBindViewHolder(holder: SmartViewHolder, position: Int) {
    holder.bindItem(getItem(position))
  }

  inner class SmartViewHolder(private val binding: ItemSmartBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindItem(item: CatalogItem) = binding.apply {
      itemView.setOnClickListener {
        item.name?.let { root.context.toast(it) }
      }
      tvTitle.text = item.name
      ivIcon.load(item.imageUrl)
    }
  }
}