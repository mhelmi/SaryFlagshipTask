package com.github.mhelmi.saryflagship.di

import com.github.mhelmi.saryflagship.di.annotations.IoScheduler
import com.github.mhelmi.saryflagship.di.annotations.MainScheduler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class SchedulersModule {

  @IoScheduler
  @Provides
  @Singleton
  fun provideIoScheduler(): Scheduler = Schedulers.io()

  @MainScheduler
  @Provides
  @Singleton
  fun provideMainScheduler(): Scheduler = AndroidSchedulers.mainThread()
}