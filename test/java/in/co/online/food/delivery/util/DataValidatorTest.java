package in.co.online.food.delivery.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DataValidatorTest {
	
	@Test
	public void isNameTest() {
		assertTrue(true == DataValidator.isName("test"));
		assertTrue(true == DataValidator.isName("TEST"));
		assertTrue(true == DataValidator.isName("TeSt"));
		assertTrue(false == DataValidator.isName("123"));
		assertTrue(false == DataValidator.isName("abc123"));
		assertTrue(false == DataValidator.isName("a@3"));
	}
	
	@Test
	public void isPasswordTest() {
		assertTrue(true == DataValidator.isPassword("Abc123"));
		assertTrue(false == DataValidator.isPassword("123"));
		assertTrue(false == DataValidator.isPassword("abc123"));
		assertTrue(false == DataValidator.isPassword("abc"));
		assertTrue(false == DataValidator.isPassword("Abc"));
		assertTrue(false == DataValidator.isPassword("ABC123"));
	}
	
	@Test
	public void isPhoneNoTest() {
		assertTrue(true == DataValidator.isPhoneNo("7894561231"));
		assertTrue(false == DataValidator.isPhoneNo("78945abc"));
		assertTrue(false == DataValidator.isPhoneNo("789456"));
		assertTrue(false == DataValidator.isPhoneNo("5612315674"));
	}
	
	@Test
	public void isNull() {
		assertTrue(true == DataValidator.isNull(null));
		assertTrue(true == DataValidator.isNull(""));
		assertTrue(true == DataValidator.isNull(" "));
		assertTrue(false == DataValidator.isNull("sxd"));
		assertTrue(false == DataValidator.isNull("_"));
	}
	
	@Test
	public void isNotNull() {
		assertTrue(true == DataValidator.isNotNull("sxd"));
		assertTrue(true == DataValidator.isNotNull("_"));
		assertTrue(false == DataValidator.isNotNull(null));
		assertTrue(false == DataValidator.isNotNull(""));
		assertTrue(false == DataValidator.isNotNull(" "));
	}
	
	@Test
	public void isIntegerTest() {
		assertTrue(true == DataValidator.isInteger("1"));
		assertTrue(true == DataValidator.isInteger("456"));
		assertTrue(false == DataValidator.isInteger(null));
		assertTrue(false == DataValidator.isInteger(""));
		assertTrue(false == DataValidator.isInteger(" "));
		assertTrue(false == DataValidator.isInteger("asd"));
		assertTrue(false == DataValidator.isInteger("a1v2"));
		assertTrue(false == DataValidator.isInteger("A@3"));
	}
	
	@Test
	public void isLongTest() {
		assertTrue(true == DataValidator.isLong("1"));
		assertTrue(true == DataValidator.isLong("456"));
		assertTrue(false == DataValidator.isLong(null));
		assertTrue(false == DataValidator.isLong(""));
		assertTrue(false == DataValidator.isLong(" "));
		assertTrue(false == DataValidator.isLong("asd"));
		assertTrue(false == DataValidator.isLong("a1v2"));
		assertTrue(false == DataValidator.isLong("A@3"));
	}
	
	@Test
	public void isEmailTest() {
		assertTrue(true == DataValidator.isEmail("test@gmail.com"));
		assertTrue(true == DataValidator.isEmail("test123@gmail.com"));
		assertTrue(true == DataValidator.isEmail("test.123@gmail.com"));
		assertTrue(true == DataValidator.isEmail("123@gmail.com"));
		assertTrue(false == DataValidator.isEmail("123gmail.com"));
		assertTrue(false == DataValidator.isEmail("@gmail.com"));
	}
	
	@Test
	public void isDateTest() {
		assertTrue(true == DataValidator.isDate("01/01/2000"));
		assertTrue(true == DataValidator.isDate("31/01/2000"));
		assertTrue(false == DataValidator.isDate(null));
	}

}
