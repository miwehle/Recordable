package com.baeldung.interceptor;

import java.lang.annotation.Annotation;
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
    String parameters =
        parameterValues(ctx.getParameters(), ctx.getMethod().getParameterAnnotations(), ctx);

    return recorder.record(result, clazz, method, parameters);
  }

  String parameterValues(Object[] parameters, Annotation[][] annotations, InvocationContext ctx) {
    String result = "";

    int n = parameters.length;
    for (int i = 0; i < n; i++) {
      boolean recordParameter = false;
      Annotation[] annos = annotations[i];
      for (int j = 0; j < annos.length; j++) {
        Annotation anno = annos[j];
        if (anno.toString().contains("RecordableParameter"))
          recordParameter = true;
      }

      if (recordParameter) {
        Object param = parameters[i];
        result += param.toString();
        if (i < n - 1)
          result += ", ";
      }
    }

    System.out.println(result);
    return result;
  }

}
