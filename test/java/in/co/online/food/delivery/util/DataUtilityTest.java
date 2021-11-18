package in.co.online.food.delivery.util;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import in.co.online.food.delivery.FoodDeliveryApplicationTests;
import junit.framework.Assert;

public class DataUtilityTest extends FoodDeliveryApplicationTests {
	
	@Test
	public void getStringTest() {
		assertEquals("Hello", DataUtility.getString("Hello "));
		assertNotEquals("Hello ", DataUtility.getString("Hello "));
	}
	
	@Test
	public void getStringDataTest() {
		Object obj = "Hello";
		assertEquals("Hello", DataUtility.getStringData(obj));
		
		int i = 1;
		assertEquals("1", DataUtility.getStringData(i));
		
		Long l = 1L;
		assertEquals("1", DataUtility.getStringData(l));
	}
	
	@Test
	public void getIntTest() {
		assertEquals(1, DataUtility.getInt("1"));
		
		assertEquals(0, DataUtility.getInt("a"));
	}
	
	@Test
	public void getLongTest() {
		assertEquals(1L, DataUtility.getLong("1"));
		
		assertEquals(0, DataUtility.getLong("a"));
	}
	
	@Test
	public void getDateTest() {
		Date date = new Date();
		Date actualDate = DataUtility.getDate((date.getMonth() + 1) + "/" + date.getDate() + "/" + date.getYear());
		assertTrue(date.getDate() == actualDate.getDate());
		assertTrue(date.getMonth() == actualDate.getMonth());
	}
	
	@Test
	public void getDate1Test() {
		Date date = new Date();
		Date actualDate = DataUtility.getDate((date.getMonth() + 1) + "/" + date.getDate() + "/" + date.getYear());
		assertTrue(date.getDate() == actualDate.getDate());
		assertTrue(date.getMonth() == actualDate.getMonth());
	}
	
	@Test
	public void getDateStringTest() {
		Date date = new Date();
		String actualDate = DataUtility.getDateString(new Date());
		assertTrue(((new Date().getMonth() + 1) + "/" + new Date().getDate() + "/" + (new Date().getYear() + 1900)).equals(actualDate));
	}
	
	@Test
	public void getDateString1Test() {
		Date date = new Date();
		String actualDate = DataUtility.getDateString1(new Date());
		assertTrue(((new Date().getYear() + 1900) + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate()).equals(actualDate));
	}
	
	@Test
	public void getTimestampTest() {
		Object timestamp = DataUtility.getTimestamp(new Date().getTime());
		assertTrue(timestamp instanceof Timestamp);
	}
	
	@Test
	public void getTimestampLongTest() {
		Long time = DataUtility.getTimestamp(new Timestamp(new Date().getTime()));
		assertTrue(time instanceof Long);
	}
	
	@Test
	public void getCurrentTimestampTest() {
		Object timestamp = DataUtility.getTimestamp(new Date().getTime());
		assertTrue(timestamp instanceof Timestamp);
	}
	
	@Test
	public void generatePasswordTest() {
		String password = DataUtility.generatePassword();
		assertTrue(password.trim().length() == 6);
	}
	
	@Test
	public void getDateDiffrenceTest() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -2);
		Date date = new Date(calendar.getTimeInMillis());
		Long diff = DataUtility.getDateDiffrence(date);
		System.out.println("date:" + date);
		System.out.println("diff:" + diff);
		assertTrue(-2L == diff);
	}

}
