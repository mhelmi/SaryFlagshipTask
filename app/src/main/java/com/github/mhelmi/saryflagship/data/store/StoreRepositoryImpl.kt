package com.github.mhelmi.saryflagship.data.store

import com.github.mhelmi.saryflagship.data.store.sources.remote.StoreApiService
import com.github.mhelmi.saryflagship.domain.store.StoreRepository
import com.github.mhelmi.saryflagship.domain.store.models.Banner
import com.github.mhelmi.saryflagship.domain.store.models.Catalog
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoreRepositoryImpl @Inject constructor(
  private val storeApiService: StoreApiService
) : StoreRepository {
  override fun getBanners(): Single<List<Banner>> = storeApiService.getBanners()
    .map { it.result }

  override fun getCatalog(): Single<List<Catalog>> = storeApiService.getCatalog()
    .map { it.catalogs }
}