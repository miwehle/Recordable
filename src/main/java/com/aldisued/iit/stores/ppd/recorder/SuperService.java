package com.aldisued.iit.stores.ppd.recorder;

public class SuperService {

  @Recordable
  public MyValueObject deliverService(@RecordableParam String uid, String unused,
      @RecordableParam String anotherId, @RecordableParam(fields = {"getName"}) MyValueObject vo) {
    return new MyValueObject(42, "Deep Thought");
  }

}
