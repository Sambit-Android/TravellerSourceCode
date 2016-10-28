package com.pgrs.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("file:WebContent/WEB-INF/config/web-application-config.xml")
public class DummyTest {

	@Test(expected = IllegalArgumentException.class)
	  public void testExceptionIsThrown() {
		DummyTest tester = new DummyTest();
	    tester.multiply(1000, 5);
	  }

	  @Test
	  public void testMultiply() {
		  DummyTest tester = new DummyTest();
	    assertEquals("10 x 5 must be 50", 50, tester.multiply(10, 5));
	  }
	  
	  public int multiply(int x, int y) {
		    // the following is just an example
		    if (x > 999) {
		      throw new IllegalArgumentException("X should be less than 1000");
		    }
		    return x / y;
		  }

}
