package junitcode;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class IgnoreTest {
	
	@Ignore("this test is deprecated")
	@Test(timeout=1)
	public void test() {
		fail("Not yet implemented");
	}

}
