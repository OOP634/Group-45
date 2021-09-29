package in.co.online.food.delivery.util;

import java.util.HashMap;


public class EmailBuilder {

	
	public static String getUserRegistration(HashMap<String, String> map) {
		StringBuilder msg = new StringBuilder();

		msg.append("<HTML><BODY>");
		msg.append("<H1>Your Registation  Completed</H1>");
		msg.append("<H3>Your Registation Detail  !! " + map.get("firstName") + " " + map.get("lastName") + "</H3>");
		msg.append("<P><B>To access account user Login Id : " + map.get("login") + "<BR>" + " Password : "
				+ map.get("password") + "</B></p>");
		msg.append("</BODY></HTML>");

		return msg.toString();
	}

	

}
