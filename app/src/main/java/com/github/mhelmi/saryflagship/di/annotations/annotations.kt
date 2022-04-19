package com.github.mhelmi.saryflagship.di.annotations

import javax.inject.Qualifier


@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoScheduler

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainScheduler