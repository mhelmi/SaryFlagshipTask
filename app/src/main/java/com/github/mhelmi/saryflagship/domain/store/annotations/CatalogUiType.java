package com.github.mhelmi.saryflagship.domain.store.annotations;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@StringDef({CatalogUiType.GRID, CatalogUiType.LINEAR, CatalogUiType.SLIDER})
public @interface CatalogUiType {
  String GRID = "grid";
  String LINEAR = "linear";
  String SLIDER = "slider";
}