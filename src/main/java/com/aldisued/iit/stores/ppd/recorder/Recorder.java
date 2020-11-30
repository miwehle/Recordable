package com.aldisued.iit.stores.ppd.recorder;

import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Recorder {

  static String testCase;
  
  static int sequentialNumber;
  
  public static void setTestCase(String tc) {    
    testCase = tc;
    sequentialNumber = 1;
    createOutputDir(tc);
  }

  public <T> T record(T obj, String callingClass, String callingMethod, String callIdentifier) {
    if (testCase == null)
      return obj;
    
    try {      
      ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
      mapper.setSerializationInclusion(Include.NON_NULL);

      // Serialize date and time types in short form
      mapper.registerModule(new JavaTimeModule());

      File file = new File(outputFile("RETURN_VALUE " + callingClass + "#" + callingMethod + " (" + callIdentifier + ")"));
      if (file.exists()) {
        throw new FileAlreadyExistsException(file.getPath());
      }
      
      mapper.writeValue(file, obj);

      return obj;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    
  }
    
  private static void createOutputDir(String testCase) {
    try {
      Path path = Paths.get(outputDir(testCase));
      Files.createDirectories(path);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  
  private static String outputFile(String name) {
    return outputFile(testCase, name);
  }
  
  public static String outputFile(String testCase, String name) {
    return outputDir(testCase) + "\\" + name + ".json";
  }

  private static String outputDir(String testCase) {
    return System.getProperty("user.home") + "\\recordeddata\\tc" + testCase;
  }
  
}
