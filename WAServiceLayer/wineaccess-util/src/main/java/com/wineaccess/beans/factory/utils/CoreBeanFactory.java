package com.wineaccess.beans.factory.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author jyoti.yadav@globallogic.com
 */
public class CoreBeanFactory implements ApplicationContextAware {
	
	static ApplicationContext appContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		CoreBeanFactory.appContext = applicationContext;
	}

	public static Object getBean(String beanName){
		return appContext.getBean(beanName);
	}
}
