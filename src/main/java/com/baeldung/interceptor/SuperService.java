package com.baeldung.interceptor;

public class SuperService {
  
  @Recordable
  public MyValueObject deliverService(String uid) {
    return new MyValueObject(42, "Deep Thought");
  }
  
}
