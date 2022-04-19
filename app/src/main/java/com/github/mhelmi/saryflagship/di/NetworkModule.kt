package com.github.mhelmi.saryflagship.di

import com.github.mhelmi.saryflagship.BuildConfig
import com.github.mhelmi.saryflagship.core.retrofit.SaryHeadersInterceptor
import com.github.mhelmi.saryflagship.data.store.sources.remote.StoreApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
  private val TIMEOUT = if (BuildConfig.DEBUG) 120L else 60L

  @Provides
  fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
    else HttpLoggingInterceptor.Level.NONE
  }

  @Singleton
  @Provides
  fun provideOkHttpClient(
    saryHeadersInterceptor: SaryHeadersInterceptor,
    httpLoggingInterceptor: HttpLoggingInterceptor
  ): OkHttpClient {
    return OkHttpClient.Builder()
      .readTimeout(TIMEOUT, TimeUnit.SECONDS)
      .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
      .addInterceptor(saryHeadersInterceptor)
      .addInterceptor(httpLoggingInterceptor)
      .build()
  }

  @Provides
  @Singleton
  fun provideGson(): Gson = GsonBuilder().create()

  @Provides
  @Singleton
  fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory =
    GsonConverterFactory.create(gson)

  @Provides
  @Singleton
  fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory =
    RxJava2CallAdapterFactory.create()

  @Singleton
  @Provides
  fun provideMainRetrofit(
    okHttpClient: OkHttpClient,
    gsonConverterFactory: GsonConverterFactory,
    rxJava2CallAdapterFactory: RxJava2CallAdapterFactory
  ): Retrofit {
    return Retrofit.Builder()
      .baseUrl(BuildConfig.BASE_URL)
      .addConverterFactory(gsonConverterFactory)
      .addCallAdapterFactory(rxJava2CallAdapterFactory)
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  @Provides
  @Singleton
  fun provideStoreApiService(retrofit: Retrofit): StoreApiService =
    retrofit.create(StoreApiService::class.java)
}