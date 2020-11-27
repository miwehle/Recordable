package com.baeldung.interceptor;

public class SuperService {
  
  @Recordable
  public MyValueObject deliverService(@RecordableParameter String uid, String unused, @RecordableParameter String anotherId) {
    return new MyValueObject(42, "Deep Thought");
  }
  
}
