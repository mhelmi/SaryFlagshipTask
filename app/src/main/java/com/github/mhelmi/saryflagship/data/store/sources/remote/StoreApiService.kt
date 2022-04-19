package com.github.mhelmi.saryflagship.data.store.sources.remote

import com.github.mhelmi.saryflagship.domain.store.models.GetBannersResponse
import com.github.mhelmi.saryflagship.domain.store.models.GetCatalogResponse
import io.reactivex.Single
import retrofit2.http.GET

interface StoreApiService {

  @GET("v2.5.1/baskets/325514/banners/")
  fun getBanners(): Single<GetBannersResponse>

  @GET("baskets/325514/catalog/")
  fun getCatalog(): Single<GetCatalogResponse>
}