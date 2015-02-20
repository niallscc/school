package com.google.gwt.sample.stockwatcher.client;

import com.google.gwt.junit.client.GWTTestCase;

/**
 * GWT JUnit tests must extend GWTTestCase.
 */
public class StockWatcherTest extends GWTTestCase {

  /**
   * Must refer to a valid module that sources this class.
   */
  public String getModuleName() {
    return "com.google.gwt.sample.stockwatcher.StockWatcher";
  }

  /**
   * Add as many tests as you like.
   */
  public void testSimple() {
    assertTrue(true);
  }
  /**
   *  Verify that the instance fields in the StockPrice class are set correctly.  
   */ 
  public void testStockPriceCtor() {  
	  String symbol = "XYZ";  
	  double price = 70.0;  
	  double change = 2.0;  
	  double changePercent = 100.0 * change / price;
	  StockPrice sp = new StockPrice(symbol, price, change);  
	  assertNotNull(sp);  
	  assertEquals(symbol, sp.getSymbol());  
	  assertEquals(price, sp.getPrice(), 0.001);  
	  assertEquals(change, sp.getChange(), 0.001);  
	  assertEquals(changePercent, sp.getChangePercent(), 0.001); 
  }

  public void testSameNameDoesntGetAddedNegative(){
	 
	  StockWatcher sw = new StockWatcher();
	  sw.accessorAddStock("ABC");
	  assertEquals(1, sw.getStocks().size());
	  sw.accessorAddStock("ABC");
	  assertEquals(1, sw.getStocks().size());
	  
  }
  public void testInvalidNameDoesntGetAdded(){
	  StockWatcher sw = new StockWatcher();
	  sw.accessorAddStock("!^&*@&$@))@*_$+_@!)+!@+)_*$&*");
	  assertEquals(0, sw.getStocks().size());

  }
  public void testNameLengthNegative(){
	  StockWatcher sw = new StockWatcher();
	  sw.accessorAddStock("ABVDEFT");
	  assertEquals(0, sw.getStocks().size());

  }
  
  public void testUppercaseTransitionPositive(){
	  StockWatcher sw = new StockWatcher();
	  sw.accessorAddStock("abc");
	  assertTrue("ERROR, did not convert to uppecase", sw.getStocks().get(0).equals("ABC"));
  }
  public void testRemoveReAddStock(){
	  StockWatcher sw = new StockWatcher();
	  sw.accessorAddStock("abc");
	  assertEquals(1, sw.getStocks().size());
	  sw.removeStockAccessor("ABC");
	  assertEquals(0, sw.getStocks().size());
	  sw.accessorAddStock("abc");
	  assertEquals(1, sw.getStocks().size());
  }
}
