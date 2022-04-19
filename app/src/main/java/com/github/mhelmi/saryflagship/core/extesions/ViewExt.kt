package com.github.mhelmi.saryflagship.core.extesions

import android.view.LayoutInflater
import android.view.ViewGroup

val ViewGroup.layoutInflater: LayoutInflater get() = LayoutInflater.from(this.context)
