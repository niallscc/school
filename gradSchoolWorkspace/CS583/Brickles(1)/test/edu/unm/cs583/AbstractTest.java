package edu.unm.cs583;

import java.io.*;

public abstract class AbstractTest {

  public AbstractTest(FileWriter file) {
      log = file;
  }

  public void logInfo(String logInfo){
      try{
          log.write(logInfo);
      }catch(IOException e){
          e.printStackTrace();
      }
  }

  public abstract void functionalSuite();
  public abstract void structuralSuite();
  public abstract void interactionSuite();
  public abstract void baselineSuite();
  public abstract void regressionSuite();

  public abstract boolean classInvariant();

  protected void startTestReport(){
      try{
        log.write("Test Report for "+className+"\n\n");
      }catch(IOException e){
         e.printStackTrace();
      }
  }

  protected void logTestResult(String description, boolean result){
      if(result){
          reportSuccess(description);
      }
      else{
          reportFailure(description);
      }
  }

  protected void reportSuccess(String test){
      try{
        log.write("Test "+ test +" succeeded.\n\n");
      }catch(IOException e){
         e.printStackTrace();
      }
  }

  protected void reportFailure(String test){
      try{
        log.write("Test "+ test +" failed.\n\n");
      }catch(IOException e){
         e.printStackTrace();
      }
  }

  protected void finishTestProcess(){
      try{
          log.close();
      }catch(IOException e){
         e.printStackTrace();
      }
  }

   protected void testProcess(){
      startTestReport();
      showTestMenu();
      testDispatch(getSelection());
      finishTestProcess();
  }

  protected void showTestMenu(){
      System.out.println("Test menu for " + className+"\n");
      System.out.println("Select number corresponding to desired test"+"\n");
      System.out.println("0. Exit Test Menu"+"\n");
	System.out.println("1. Functional tests"+"\n");
	System.out.println("2. Structural tests"+"\n");
	System.out.println("3. Interaction tests"+"\n");
  }

  protected int getSelection(){
      InputStreamReader isr;
      BufferedReader keyb;
      String response = " ";
      isr = new InputStreamReader(System.in);
      keyb = new BufferedReader(isr);
      System.out.print("Enter response and <RETURN>: ");
      try{
          response = keyb.readLine();
      }catch(IOException e){
          e.printStackTrace();
      }
      return Integer.parseInt(response);
  }

  protected void testDispatch(int which){
      if(which < 0){
          System.out.println("Selection out or Range\n");
      }
      if(which == 0){
          System.out.println("Test completed\n");
          System.exit(0);
      }
  }


 /*
  public void finalize(){
      try{
          log.close();
      }catch(IOException e){
          e.printStackTrace();
      }
  }
   */
  protected FileWriter log;
  protected String className;
  protected String fileName;
  protected Object OUT;
}