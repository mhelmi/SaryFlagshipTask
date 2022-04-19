package com.github.mhelmi.saryflagship.domain.store.models

import com.google.gson.annotations.SerializedName

data class GetCatalogResponse(
  @SerializedName("message") val message: String,
  @SerializedName("result") val catalogs: List<Catalog>,
  @SerializedName("status") val status: Boolean
)