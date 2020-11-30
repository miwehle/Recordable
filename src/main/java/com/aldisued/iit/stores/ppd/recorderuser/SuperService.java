package com.aldisued.iit.stores.ppd.recorderuser;

import com.aldisued.iit.stores.ppd.recorder.Recordable;
import com.aldisued.iit.stores.ppd.recorder.RecordableParam;

public class SuperService {

  @Recordable
  public MyValueObject deliverService(@RecordableParam String uid, String unused,
      @RecordableParam String anotherId, @RecordableParam(fields = {"getName"}) MyValueObject vo) {
    return new MyValueObject(42, "Deep Thought");
  }

}
