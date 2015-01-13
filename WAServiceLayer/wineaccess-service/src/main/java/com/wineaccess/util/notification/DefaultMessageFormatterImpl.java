package com.wineaccess.util.notification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * <p>
 * Default Implementation of the IMessageFormatter. It provides a basic String
 * formating feature to replace the parameter enclosed in {PARAM-KEY} with the
 * provided PARAM-VALUE at run-time. For ex : {Client-Name} is offering
 * {Amount}% discount to all of its customers.
 * 
 * Values supplied at run-time: Client-Name - citi-bank Amount - 10 Expected
 * outcome is: citi-bank is offering 10% discount to all of its customers.
 * <p>
 * 
 * @author tarun.kumar
 * 
 */
public class DefaultMessageFormatterImpl implements IMessageFormatter {

	public String format(String message, Map<String, String> notificationAttributes) {
		int startIndex = 0;
		int endIndex = 0;

		Collection<String> paramCollection = new ArrayList<String>();
		while (true) {
			startIndex = message.indexOf('{', endIndex);
			endIndex = message.indexOf('}', startIndex);
			if (startIndex == -1 || endIndex == -1) {
				break;
			}
			String param = message.substring(startIndex + 1, endIndex);
			paramCollection.add(param);
		}

		for (String param : paramCollection) {
			String replaceParam = "{" + param + "}";
			if (notificationAttributes.get(param) != null ) {
				message = message.replace(replaceParam,
						notificationAttributes.get(param).toString());
			}
		}
		return message;
	}
}
