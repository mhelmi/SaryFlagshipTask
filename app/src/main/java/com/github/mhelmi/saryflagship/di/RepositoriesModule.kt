package com.github.mhelmi.saryflagship.di

import com.github.mhelmi.saryflagship.data.store.StoreRepositoryImpl
import com.github.mhelmi.saryflagship.domain.store.StoreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {

  @Singleton
  @Binds
  abstract fun bindStoreRepository(storeRepository: StoreRepositoryImpl): StoreRepository
}