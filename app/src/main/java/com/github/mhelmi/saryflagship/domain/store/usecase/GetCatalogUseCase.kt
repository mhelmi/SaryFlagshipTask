package com.github.mhelmi.saryflagship.domain.store.usecase

import com.github.mhelmi.saryflagship.domain.store.StoreRepository
import com.github.mhelmi.saryflagship.domain.store.annotations.CatalogDataType
import com.github.mhelmi.saryflagship.domain.store.models.Catalog
import io.reactivex.Single
import javax.inject.Inject

class GetCatalogUseCase @Inject constructor(
  private val storeRepository: StoreRepository
) {
  operator fun invoke(): Single<List<Catalog>> = storeRepository.getCatalog()
    .map {
      // ignore the undefined dataTypes from the catalog list
      it.filter { catalog -> catalog.dataType in CatalogDataType.validDataTypes }
    }
}