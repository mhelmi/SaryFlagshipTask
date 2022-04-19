package com.github.mhelmi.saryflagship.domain.store.models

import com.google.gson.annotations.SerializedName

data class GetBannersResponse(
  @SerializedName("result") val result: List<Banner>,
  @SerializedName("status") val status: Boolean
)