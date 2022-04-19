package com.github.mhelmi.saryflagship.domain.store.annotations;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@StringDef({CatalogDataType.BANNER, CatalogDataType.GROUP, CatalogDataType.SMART})
public @interface CatalogDataType {
  String BANNER = "banner";
  String GROUP = "group";
  String SMART = "smart";

  // There is some types not recognized in documentation I will consider it as invalid types and ignore it.
  String[] validDataTypes = {BANNER, GROUP, SMART};
}