package com.github.mhelmi.saryflagship.domain.store

import com.github.mhelmi.saryflagship.domain.store.models.Banner
import com.github.mhelmi.saryflagship.domain.store.models.Catalog
import io.reactivex.Single

interface StoreRepository {
  fun getBanners(): Single<List<Banner>>
  fun getCatalog(): Single<List<Catalog>>
}