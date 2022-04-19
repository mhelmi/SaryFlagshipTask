package com.github.mhelmi.saryflagship.domain.store.models

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CatalogItem(
  @SerializedName("deep_link") val deepLink: String?,
  @SerializedName("empty_content_image") val emptyContentImage: String?,
  @SerializedName("empty_content_message") val emptyContentMessage: String?,
  @SerializedName("group_id") val groupId: Int?,
  @SerializedName("has_data") val hasData: Boolean?,
  @SerializedName("image") val imageUrl: String?,
  @SerializedName("name") val name: String?,
  @SerializedName("show_in_brochure_link") val showInBrochureLink: Boolean?,
  @SerializedName("show_unavailable_items") val showUnavailableItems: Boolean?
) : Parcelable {
  companion object {
    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CatalogItem>() {
      override fun areItemsTheSame(oldItem: CatalogItem, newItem: CatalogItem): Boolean {
        return oldItem.imageUrl == newItem.imageUrl
      }

      override fun areContentsTheSame(oldItem: CatalogItem, newItem: CatalogItem): Boolean {
        return oldItem == newItem
      }
    }
  }
}