package com.baeldung.interceptor;
import java.io.File;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
    String fileName = Recorder.outputFile(TC, "RETURN_VALUE SuperService#deliverService (2)");
    File recordedFile = new File(fileName);
    recordedFile.delete();
    // 2) get service (the SUT)
    SuperService service = container.select(SuperService.class).get();
    
    // exercise
    service.deliverService("123456");

    // verify
    // 1)
    Assert.assertTrue(RecordableInterceptor.calledBefore);
    Assert.assertTrue(RecordableInterceptor.calledAfter);
    // 2) recorded file should exist
    Assert.assertTrue(recordedFile.exists());
  }

}
