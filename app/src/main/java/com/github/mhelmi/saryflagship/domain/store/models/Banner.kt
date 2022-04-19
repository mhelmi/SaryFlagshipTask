package com.github.mhelmi.saryflagship.domain.store.models

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Banner(
  @SerializedName("id") val id: Int?,
  @SerializedName("image") val imageUrl: String?,
  @SerializedName("is_available") val isAvailable: Boolean?,
  @SerializedName("photo") val photo: String,
  var isActive: Boolean = false
) : Parcelable {
  companion object {
    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Banner>() {
      override fun areItemsTheSame(oldItem: Banner, newItem: Banner): Boolean {
        return oldItem.imageUrl == newItem.imageUrl
      }

      override fun areContentsTheSame(oldItem: Banner, newItem: Banner): Boolean {
        return oldItem == newItem
      }
    }
  }
}