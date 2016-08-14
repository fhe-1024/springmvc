package junitcode;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @BeforeClass在类层次 只会执行一次 必須是静态static
 * @author hefei
 *
 */
public class BeforeClassTest {
	
	private static String str="123";
	
	@BeforeClass
	public static void init(){
		str="456";
	}
	
	@Test
	public void test1(){
		assertEquals("test1","456", str);
	}
	
	@Test
	public void test2(){
		assertEquals("test2","456", str);
	}
	@AfterClass
	public static void after(){
		assertFalse(false);
	}
	
}
