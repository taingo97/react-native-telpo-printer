package com.reactnativetelpoprinter;
import com.common.apiutil.printer.UsbThermalPrinter;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import java.util.Map;
import java.util.HashMap;
import com.facebook.react.bridge.Promise;

public class UsbPrinterModule extends ReactContextBaseJavaModule{
  UsbThermalPrinter mUsbThermalPrinter;
  String textPrint = "";
  UsbPrinterModule(ReactApplicationContext context) {
    super(context);
    mUsbThermalPrinter = new UsbThermalPrinter(context);
  }
  public String getName() {
    return "UsbPrinterModule";
  }
  @ReactMethod(isBlockingSynchronousMethod = true)
  public void printerText(String text, final Promise p) {
    try{
      textPrint = text;
      new contentPrintThread().start();
    }catch (Exception e) {
      e.printStackTrace();
      p.reject("" + 0, e.getMessage());
    }
  }
  @ReactMethod(isBlockingSynchronousMethod = true)
  public void inTextTest(String text) {
    try{
      textPrint = text;
      new contentPrintThread().start();
    }catch (Exception e) {
      e.printStackTrace();
    }
  }
  private class contentPrintThread extends Thread{
    public void run(){
      super.run();
      try{
        mUsbThermalPrinter.reset();
        mUsbThermalPrinter.addString("Tes in may post");
        mUsbThermalPrinter.printString();
        mUsbThermalPrinter.walkPaper(20);
      }
      catch (Exception ex){
        ex.printStackTrace();
      }
    }
  }
}
