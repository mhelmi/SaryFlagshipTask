package com.github.mhelmi.saryflagship.domain.store.models

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import com.github.mhelmi.saryflagship.domain.store.annotations.CatalogDataType
import com.github.mhelmi.saryflagship.domain.store.annotations.CatalogUiType
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Catalog(
  @SerializedName("data") val catalogItems: List<CatalogItem>,
  @CatalogDataType @SerializedName("data_type") val dataType: String?,
  @SerializedName("id") val id: Int?,
  @SerializedName("row_count") val rowCount: Int,
  @SerializedName("show_title") val showTitle: Boolean,
  @SerializedName("title") val title: String?,
  @CatalogUiType @SerializedName("ui_type") val uiType: String?
) : Parcelable {

  val notNullCatalogItems: List<CatalogItem>
    get() {
      return catalogItems.filter { it.imageUrl.isNullOrEmpty().not() }
    }

  companion object {
    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Catalog>() {
      override fun areItemsTheSame(oldItem: Catalog, newItem: Catalog): Boolean {
        return oldItem.id == newItem.id
      }

      override fun areContentsTheSame(oldItem: Catalog, newItem: Catalog): Boolean {
        return oldItem == newItem
      }
    }
  }
}