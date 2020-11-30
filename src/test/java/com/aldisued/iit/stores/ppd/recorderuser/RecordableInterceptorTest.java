package com.aldisued.iit.stores.ppd.recorderuser;

import static org.junit.Assert.assertEquals;
import java.io.File;
import java.lang.reflect.Method;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.aldisued.iit.stores.ppd.recorder.RecordableInterceptor;
import com.aldisued.iit.stores.ppd.recorder.Recorder;

public class RecordableInterceptorTest {
  Weld weld;
  WeldContainer container;

  final String TC = "47.11";

  @Before
  public void init() {
    weld = new Weld();
    container = weld.initialize();
    Recorder.setTestCase(TC);
  }

  @After
  public void shutdown() {
    weld.shutdown();
  }

  @Test
  public void givenTheService_whenMethodAndInterceptorExecuted_thenOK() {
    // setup
    // 1) delete the recorded file
    String fileName = Recorder.outputFile(TC, "RETURN_VALUE SuperService#deliverService (123456, _, 42, Yoda)");
    File recordedFile = new File(fileName);
    recordedFile.delete();
    // 2) get service (the SUT)
    SuperService service = container.select(SuperService.class).get();

    // exercise
    MyValueObject yoda = new MyValueObject(823, "Yoda");
    service.deliverService("123456", "unused", "42", yoda);

    // verify
    // 1)
    Assert.assertTrue(RecordableInterceptor.calledBefore);
    Assert.assertTrue(RecordableInterceptor.calledAfter);
    // 2) recorded file should exist
    Assert.assertTrue(recordedFile.exists());
  }

  @Test
  public void invoke() throws Exception {
    MyValueObject vo = new MyValueObject(42, "Deep Thought");
    Method getAgeMethod = MyValueObject.class.getMethod("getAge");
    
    Object age = getAgeMethod.invoke(vo);
    
    System.out.println("obj = " + age);
    assertEquals(42, age);
  }

}
