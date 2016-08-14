package junitcode;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BeforeTest {
	
	private String str="123";
	
	@Before
	public void init(){
		str="456";
	}
	
	
	@Test
	public void test() {
		assertEquals("test1","456", str);
	}

}
