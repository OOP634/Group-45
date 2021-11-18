package in.co.online.food.delivery.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class EmailBuilderTest {
	
	@Test
	public void getForgetPasswordMessageTest() {
		HashMap<String, String> map = new HashMap<>();
		map.put("firstName", "Steve");
		map.put("lastName", "Scott");
		map.put("login", "steve scott");
		map.put("password", "Test@123");
		
		String expectedMessage = "<HTML><BODY><H1>Your password is reccovered !! Steve Scott</H1><P><B>To access account user Login Id : steve scott<BR> Password : Test@123</B></p></BODY></HTML>";
		assertTrue(EmailBuilder.getForgetPasswordMessage(map).equals(expectedMessage));
		assertFalse(EmailBuilder.getForgetPasswordMessage(map).equals("qwertyghjk"));
	}
	
	@Test
	public void getUserRegistrationTest() {
		HashMap<String, String> map = new HashMap<>();
		map.put("firstName", "Steve");
		map.put("lastName", "Scott");
		map.put("login", "steve scott");
		map.put("password", "Test@123");
		
		String expectedMessage = "<HTML><BODY><H1>Your Registation  Completed</H1><H3>Your Registation Detail  !! Steve Scott</H3><P><B>To access account user Login Id : steve scott<BR> Password : Test@123</B></p></BODY></HTML>";
		assertTrue(EmailBuilder.getUserRegistration(map).equals(expectedMessage));
		assertFalse(EmailBuilder.getUserRegistration(map).equals("jhgb c"));
	}

}
