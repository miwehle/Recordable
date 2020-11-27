package com.baeldung.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Recordable
@Interceptor
public class RecordableInterceptor {
  public static boolean calledBefore = false;
  public static boolean calledAfter = false;

  Recorder recorder = new Recorder();

  @AroundInvoke
  public Object recordMethod(InvocationContext ctx) throws Exception {
    calledBefore = true;
    Object result = ctx.proceed();

    // System.out.println("method : " + ctx.getMethod());
    // System.out.println("parameters: " + ctx.getParameters()[0]);
    // ctx.getMethod().getParameterAnnotations()

    calledAfter = true;

    String method = ctx.getMethod().getName();
    String clazz = ctx.getMethod().getDeclaringClass().getSimpleName();

    return recorder.record(result, clazz, method, "2");
  }

}
