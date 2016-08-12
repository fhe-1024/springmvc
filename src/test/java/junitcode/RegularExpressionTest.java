package junitcode;

import static org.junit.Assert.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.BeforeClass;
import org.junit.Test;

public class RegularExpressionTest {
	private static String zipRegEx = "^\\d{5}([\\-]\\d{4})?$";
	private static Pattern pattern;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("before");
		pattern = Pattern.compile(zipRegEx);
	}

	@Test
	public void verifyGoodZipCode() throws Exception {
		System.out.println("test");
		Matcher mtcher = pattern.matcher("221");
		boolean isValid = mtcher.matches();
		assertTrue("Pattern did not validate zip code", isValid);
	}
}
