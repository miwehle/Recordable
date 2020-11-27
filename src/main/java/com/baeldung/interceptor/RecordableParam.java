package com.baeldung.interceptor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RecordableParam {
  String[] fields() default {};
}
