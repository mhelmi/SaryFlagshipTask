package com.github.mhelmi.saryflagship.ui.store.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.mhelmi.saryflagship.core.extesions.load
import com.github.mhelmi.saryflagship.core.extesions.toast
import com.github.mhelmi.saryflagship.databinding.ItemGroupBinding
import com.github.mhelmi.saryflagship.domain.store.models.CatalogItem

class GroupViewAdapter :
  ListAdapter<CatalogItem, GroupViewAdapter.GroupViewHolder>(CatalogItem.DIFF_CALLBACK) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
    return GroupViewHolder(
      ItemGroupBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
  }

  override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
    holder.bindItem(getItem(position))
  }

  inner class GroupViewHolder(private val binding: ItemGroupBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindItem(item: CatalogItem) = binding.apply {
      itemView.setOnClickListener {
        item.name?.let { root.context.toast(it) }
      }
      ivIcon.load(item.imageUrl)
    }
  }
}