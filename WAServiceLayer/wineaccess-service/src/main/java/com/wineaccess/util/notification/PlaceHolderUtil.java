package com.wineaccess.util.notification;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.wineaccess.data.model.user.Persistent;

public class PlaceHolderUtil {
	
	
	public static String getPrefix(Class objectClass) {
		if (objectClass.getSimpleName().equals("UserModel")) {
			return "user";
		}
		if (objectClass.getSimpleName().equals("APIAccessCode")) {
			return "apiAccess";
		}
		return objectClass.getSimpleName();
	}
	
	public static Set<String> getAdditionalProperties(Class objectClass) {
		Set<String> propertiesKey = new HashSet<String>();
		if (objectClass.getSimpleName().equals("UserModel")){
			propertiesKey.add(getPrefix(objectClass) + "." + "activationUrl");
        	propertiesKey.add(getPrefix(objectClass) + "." + "forgotUrl");
        }
		return propertiesKey;
	}
	
	public static final Set<String> listAllKeys(Class ... objectClasses) {
		
		Set<String> propertiesKey = new HashSet<String>();
		
		for (Class objectClass : objectClasses ) {
			String prefix = getPrefix(objectClass);

			Method[] methods = objectClass.getMethods();

	        for (Method m : methods) {
	        	Class<?> returnType = m.getReturnType();
	    		if (m.getName().startsWith("get") && !returnType.equals(Class.class)) {
	    			m.setAccessible(true);
	                String key = m.getName().substring(3, 4).toLowerCase() + m.getName().substring(4);
	                propertiesKey.add(prefix + "." + key);
	            }
	        }
	        propertiesKey.addAll(getAdditionalProperties(objectClass));
		}
		
		return propertiesKey;
	}
	
	public static final Map<String, String> getMapFromObject(Persistent object, Class objectClass) {
		
        Map<String, String> propertyMap = new HashMap<String, String>();
        Method[] methods = objectClass.getMethods();

        for (Method m : methods) {
        	Class<?> returnType = m.getReturnType();
    		if (m.getName().startsWith("get") && !returnType.equals(Class.class)) {
    			m.setAccessible(true);
                String key = m.getName().substring(3, 4).toLowerCase() + m.getName().substring(4);
                Object retVal = null;
                try {
                    retVal = m.invoke(object, (Object[]) null);
                } catch (IllegalArgumentException e) {
                    // TODO Add Log Message
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Add Log Message
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    // TODO Add Log Message
                    e.printStackTrace();
                }

                if (null != retVal) {
                    propertyMap.put(getPrefix(objectClass) + "." + key, String.valueOf(retVal));
                } else {
                	 propertyMap.put(getPrefix(objectClass) + "." + key, String.valueOf(""));
                }
            }
        }
        return propertyMap;
    }
}
