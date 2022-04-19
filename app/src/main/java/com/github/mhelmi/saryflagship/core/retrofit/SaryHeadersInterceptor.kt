package com.github.mhelmi.saryflagship.core.retrofit

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class SaryHeadersInterceptor @Inject constructor() : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
    val request: Request = chain.request()
    val requestBuilder: Request.Builder = request.newBuilder()
      .apply {
        addHeader(Headers.DEVICE_TYPE_KEY, Headers.DEVICE_TYPE)
        addHeader(Headers.APP_VERSION_KEY, Headers.APP_VERSION)
        addHeader(Headers.ACCEPT_LANGUAGE_KEY, Headers.ACCEPT_LANGUAGE)
        addHeader(Headers.PLATFORM_KEY, Headers.PLATFORM)
        addHeader(Headers.AUTHORIZATION_KEY, Headers.TOKEN)
      }
    return chain.proceed(requestBuilder.build())
  }
}