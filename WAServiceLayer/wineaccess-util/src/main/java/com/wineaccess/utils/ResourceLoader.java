package com.wineaccess.utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Properties;

/**
 * Simple utility that loads resources via InputStream.getResourceAsStream.
 * 
 * @author jyoti.yadav@globallogic.com
 */
public class ResourceLoader {

	private static final String CONFIG_PATH = "";

	/**
	 * Utility method to get an InputStream for a given resoure path
	 * 
	 * @param String
	 *            pathToResource path relative to the calling context.
	 * @param useContextClassLoader
	 *            if <code>true</code> then use the current Thread's context
	 *            ClassLoader
	 * @return an input stream for reading the resource, or <code>null</code>
	 *         if the resource could not be found
	 * 
	 * @see java.lang.Thread#getContextClassLoader()
	 */
	private static InputStream loadResource(String pathToResource, boolean useContextClassLoader) {

		if (useContextClassLoader) {
			return Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(CONFIG_PATH + pathToResource);
		} else {
			return ResourceLoader.class.getClassLoader().getResourceAsStream(CONFIG_PATH + pathToResource);
		}
	}

	/**
	 * <p>Utility method to get an InputStream for a given resoure path.</p>
	 *   
	 * @param  pathToResource  String pathToResource path relative to the calling context.  
	 *          For example: loadResource(/app.properties, false) 
	 *          for an EAR/APP-INF/classes/app.properties
	 * @return  an input stream for reading the resource, or <code>null</code>
	 *          if the resource could not be found
	 *
	 */
	public static InputStream loadResource(String pathToResource) throws IOException {
		return loadResource(pathToResource, false);
	}

	/**
	 * <p>
	 * Utility method to get a Properties object for a given resoure path.
	 * </p>
	 * 
	 * @param pathToResource
	 *            pathToResource path relative to the calling context. 
	 * @return an Properties object read from pathToResource
	 * @exception IOException
	 *                if the resource could not be found
	 *  
	 */
	public static Properties getProperties(String pathToResource)
			throws IOException {
		return getProperties(pathToResource, false);
	}

	/**
	 * <p>
	 * Utility method to get a Properties object for a given resoure path
	 * </p>
	 * 
	 * @param String
	 *            pathToResource path relative to the calling context.
	 * @param useContextClassLoader
	 *            if <code>true</code> then use the current Thread's context
	 *            ClassLoader
	 * @return an Properties object read from pathToResource
	 * @exception IOException
	 *                if the resource could not be found
	 * 
	 * @see java.lang.Thread#getContextClassLoader()
	 */
	private static Properties getProperties(String pathToResource, boolean useContextClassLoader) throws IOException {
		InputStream input = loadResource(pathToResource, useContextClassLoader);
		if (input == null) {
			throw new IOException("Resource " + pathToResource + " not found");
		}
		Properties p = new Properties();
		p.load(input);
		return p;
	}

	/**
	 * Load a class with a given name. <p/> It will try to load the class in the
	 * following order:
	 * <ul>
	 * <li>From
	 * {@link Thread#getContextClassLoader() Thread.currentThread().getContextClassLoader()}
	 * <li>Using the basic {@link Class#forName(java.lang.String) }
	 * <li>From
	 * {@link Class#getClassLoader() ClassLoaderUtil.class.getClassLoader()}
	 * <li>From the
	 * {@link Class#getClassLoader() callingClass.getClassLoader() }
	 * </ul>
	 *
	 * @param className    The name of the class to load
	 * @param callingClass The Class object of the calling object
	 * @throws ClassNotFoundException If the class cannot be found anywhere.
	 */
	public static Class loadClass(final String className, final Class callingClass) throws ClassNotFoundException {
		Class clazz = null;
		clazz = (Class) AccessController.doPrivileged(new PrivilegedAction() {
			public Object run() {
				try {
					return Thread.currentThread().getContextClassLoader()
							.loadClass(className);
				} catch (ClassNotFoundException e) {
					return null;
				}
			}
		});

		if (clazz == null) {
			clazz = (Class) AccessController
					.doPrivileged(new PrivilegedAction() {
						public Object run() {
							try {
								return Class.forName(className);
							} catch (ClassNotFoundException e) {
								return null;
							}
						}
					});
		}

		if (clazz == null) {
			clazz = (Class) AccessController
					.doPrivileged(new PrivilegedAction() {
						public Object run() {
							try {
								return ResourceLoader.class.getClassLoader()
										.loadClass(className);
							} catch (ClassNotFoundException e) {
								return null;
							}
						}
					});
		}

		if (clazz == null) {
			clazz = (Class) AccessController
					.doPrivileged(new PrivilegedAction() {
						public Object run() {
							try {
								return callingClass.getClassLoader().loadClass(
										className);
							} catch (ClassNotFoundException e) {
								return null;
							}
						}
					});
		}
		if (clazz == null) {
			throw new ClassNotFoundException(className);
		}
		return clazz;
	}

	/**
	 * This method instanciate a class.
	 * @param clazz : Class
	 * @param constructorArgs : Object[]
	 * @return Object
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static Object instanciateClass(Class clazz, Object[] constructorArgs) throws SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
		Class[] args = null;
		if (constructorArgs != null) {
			args = new Class[constructorArgs.length];
			for (int i = 0; i < constructorArgs.length; i++) {
				if (constructorArgs[i] == null) {
					args[i] = null;
				} else {
					args[i] = constructorArgs[i].getClass();
				}
			}
		} else {
			args = new Class[0];
		}
		Constructor ctor = getConstructor(clazz, args);
		if (ctor == null) {
			String argsString = new String();
			for (int i = 0; i < args.length; i++) {
				argsString += args[i].getName() + ", ";
			}
			throw new NoSuchMethodException("could not find constructor with matching arg params: " + argsString);
		}

		return ctor.newInstance(constructorArgs);
	}

	/**
	 * This method instanciate a class.
	 * @param name : String
	 * @param constructorArgs : Object[]
	 * @return Object
	 * @throws ClassNotFoundException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static Object instanciateClass(String name, Object[] constructorArgs)
			throws ClassNotFoundException, SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException {
		Class clazz = loadClass(name, ResourceLoader.class);
		return instanciateClass(clazz, constructorArgs);

	}

	/**
	 * This method instanciate a class.
	 * @param name :name
	 * @param constructorArgs : Object[]
	 * @param callingClass : Class
	 * @return Object
	 * @throws ClassNotFoundException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static Object instanciateClass(String name,
			Object[] constructorArgs, Class callingClass)
			throws ClassNotFoundException, SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException {
		Class clazz = loadClass(name, callingClass);
		return instanciateClass(clazz, constructorArgs);

	}

	/**
	 * get parameterTypes.
	 * @param bean : Object
	 * @param methodName : String
	 * @return Class[]
	 */
	public static Class[] getParameterTypes(Object bean, String methodName) {
		if (!methodName.startsWith("set")) {
			methodName = "set" + methodName.substring(0, 1).toUpperCase()
					+ methodName.substring(1);
		}
		Method methods[] = bean.getClass().getMethods();

		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().equals(methodName)) {
				return methods[i].getParameterTypes();
			}
		}
		return new Class[] {};
	}

	/**
	 * Get method of intanciated class.
	 * @param name : String
	 * @param clazz : Class
	 * @return Method
	 */
	public static Method getMethod(String name, Class clazz) {
		Method[] methods = clazz.getMethods();
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().equals(name)) {
				return methods[i];
			}
		}
		return null;
	}

	/**
	 * get constructor of instanciated class.
	 * @param clazz : Class
	 * @param paramTypes : Class[]
	 * @return Constructor
	 */
	public static Constructor getConstructor(Class clazz, Class[] paramTypes) {
		Constructor[] ctors = clazz.getConstructors();
		for (int i = 0; i < ctors.length; i++) {
			if (ctors[i].getParameterTypes().length == paramTypes.length) {
				Class[] types = ctors[i].getParameterTypes();
				boolean match = true;
				for (int x = 0; x < types.length; x++) {
					if (paramTypes[x] == null) {
						match = true;
					} else {
						match = types[x].isAssignableFrom(paramTypes[x]);
					}
				}
				if (match){
					return ctors[i];
				}
			}
		}
		return null;
	}
}
