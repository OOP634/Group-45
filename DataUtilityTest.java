package in.co.online.food.delivery.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import in.co.online.food.delivery.FoodDeliveryApplicationTests;
import junit.framework.Assert;

public class DataUtilityTest extends FoodDeliveryApplicationTests {
	
	@Test
	public void getStringTest() {
		assertEquals("Hello", DataUtility.getString("Hello "));
	}
	
	@Test
	public void getStringDataTest() {
		Object obj = "Hello";
		assertEquals("Hello", DataUtility.getStringData(obj));
	}
	
	@Test
	public void getIntTest() {
		assertEquals(1, DataUtility.getInt("1"));
	}
	
	@Test
	public void getLongTest() {
		assertEquals(1L, DataUtility.getLong("1"));
	}

}
