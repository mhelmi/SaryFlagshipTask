package com.github.mhelmi.saryflagship.core.extesions

import android.widget.ImageView
import com.github.mhelmi.saryflagship.R
import com.squareup.picasso.Picasso

fun ImageView.load(url: String?) {
  Picasso.get()
    .load(url)
    .error(R.drawable.ic_sary_logo)
    .into(this)
}