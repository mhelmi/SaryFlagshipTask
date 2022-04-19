package com.github.mhelmi.saryflagship.domain.store.usecase

import com.github.mhelmi.saryflagship.domain.store.StoreRepository
import com.github.mhelmi.saryflagship.domain.store.models.Banner
import io.reactivex.Single
import javax.inject.Inject

class GetBannersUseCase @Inject constructor(
  private val storeRepository: StoreRepository
) {
  operator fun invoke(): Single<List<Banner>> = storeRepository.getBanners()
}