package com.wineaccess.ApplicationUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {

	public static boolean validateContentFormat(String content, String regEx) {

		Pattern pattern = Pattern.compile(regEx);

		Matcher matcher = pattern.matcher(content);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	public static boolean validateContent(String content, String regEx) {

		Pattern pattern = Pattern.compile(regEx);

		Matcher matcher = pattern.matcher(content);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	public static void main(String args[]) {
		Long value = 1L;
		System.out.println(validateContent(Long.toString(value), "\\d*"));
	}

}
