package com.aldisued.iit.stores.ppd.recorder;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RecordableParam {
  String[] fields() default {};
}
