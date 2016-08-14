package junitcode;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @BeforeClass������ ֻ��ִ��һ�� ����Ǿ�̬static
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
