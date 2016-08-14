package junitcode;

import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * 
 * by defaults Maven uses the following naming conventions when looking for
 * tests to run: Test* *Test *TestCase
 * 
 * @author fhe
 *
 */

public class ExceptionTest {
	@Test(expected = IndexOutOfBoundsException.class)
	public void testIndexOutOfBoundsException() {
		ArrayList<Object> emptyList = new ArrayList<>();
		emptyList.get(0);
	}

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void testNumberFormatException() {
		exception.expect(NumberFormatException.class);
		Integer.parseInt("aaa");
	}
	@Test
	public void helloworld(){
		assertFalse(false);
	}
	
	
	
	

}
