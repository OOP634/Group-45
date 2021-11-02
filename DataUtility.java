package in.co.online.food.delivery.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class DataUtility {

	public static final String APP_DATE_FORMAT = "MM/dd/yyyy";

	private static final SimpleDateFormat formatter = new SimpleDateFormat(APP_DATE_FORMAT);
	
	public static final String APP_DATE_FORMAT1 = "yyyy-MM-dd";

	private static final SimpleDateFormat formatter1 = new SimpleDateFormat(APP_DATE_FORMAT1);

	public static String getString(String val) {
		if (DataValidator.isNotNull(val)) {
			return val.trim();
		} else {
			return val;
		}
	}

	public static String getStringData(Object val) {

		if (val != null) {
			return val.toString();
		} else {
			return "";
		}
	}

	public static int getInt(String val) {
		if (DataValidator.isInteger(val)) {
			return Integer.parseInt(val);
		} else {
			return 0;
		}
	}

	public static long getLong(String val) {
		if (DataValidator.isLong(val)) {
			return Long.parseLong(val);
		} else {
			return 0;
		}
	}

	public static Date getDate(String val) {
		Date date = null;
		try {
			date = formatter.parse(val);
		} catch (Exception e) {

		}
		return date;
	}

	public static Date getDate1(String val) {
		Date date = null;

		try {
			date = formatter.parse(val);

		} catch (Exception e) {
		}
		return date;
	}

	public static String getDateString(Date date) {

		try {
			if (date != null) {
				return formatter.format(date);
			} else {
				return "";
			}
		} catch (Exception e) {
			return "";
		}

	}
	
	public static String getDateString1(Date date) {

		try {
			if (date != null) {
				return formatter1.format(date);
			} else {
				return "";
			}
		} catch (Exception e) {
			return "";
		}

	}

	public static Date getDate(Date date, int day) {
		return null;
	}

	public static Timestamp getTimestamp(long l) {

		Timestamp timeStamp = null;
		try {
			timeStamp = new Timestamp(l);
		} catch (Exception e) {
			return null;
		}
		return timeStamp;
	}

	public static Timestamp getTimestamp(String cdt) {

		Timestamp timeStamp = null;
		try {
			// timeStamp = new Timestamp((timeFormatter.parse(cdt)).getTime());
		} catch (Exception e) {
			return null;
		}
		return timeStamp;
	}

	public static long getTimestamp(Timestamp tm) {
		try {
			return tm.getTime();
		} catch (Exception e) {
			return 0;
		}
	}

	public static Timestamp getCurrentTimestamp() {
		Timestamp timeStamp = null;
		try {
			timeStamp = new Timestamp(new Date().getTime());
		} catch (Exception e) {
		}
		return timeStamp;

	}

	public static String generatePassword() {
		String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
		String specialCharacters = "!@#$";
		String numbers = "1234567890";
		String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
		String pass = null;
		Random random = new Random();
		int length = 6;
		char[] password = new char[length];

		password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
		password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
		password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
		password[3] = numbers.charAt(random.nextInt(numbers.length()));

		for (int i = 4; i < length; i++) {
			password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));

		}
		return String.copyValueOf(password);
	}

	public static byte[] openFile(String path) {
		byte[] encoded = null;
		try {
			encoded = Files.readAllBytes(Paths.get(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return encoded;
	}

	public static long getRandom() {
		Random rand = new Random();
		return rand.nextInt(10000);
	}

	public static long getDateDiffrence(Date sessionDate) {
		long difference_In_Days = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
			Date d2;
			d2 = sdf.parse(DataUtility.getDateString(sessionDate));
			Date d1 = sdf.parse(DataUtility.getDateString(new Date()));
			long difference_In_Time = d2.getTime() - d1.getTime();
			difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;
			System.out.println("Session Date---" + difference_In_Days);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return difference_In_Days;
	}

	public static void main(String[] args) {
		DataUtility d = new DataUtility();
		Date date = new Date();

		System.out.println(generatePassword());
	}

}
