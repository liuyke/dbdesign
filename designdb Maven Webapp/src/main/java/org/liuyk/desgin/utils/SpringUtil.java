package org.liuyk.desgin.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringUtil implements ApplicationContextAware {
	
	private static ApplicationContext mApplicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringUtil.mApplicationContext = applicationContext;
	}

	public static Object getBean(String name) throws BeansException {
		return mApplicationContext.getBean(name);
	}

	public static Object getBean(String name, Class<?> requiredType) throws BeansException {
		return mApplicationContext.getBean(name, requiredType);
	}
	
	public static boolean containsBean(String name) {
		return mApplicationContext.containsBean(name);
	}

	public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
		return mApplicationContext.isSingleton(name);
	}

	public static Class<?> getType(String name) throws NoSuchBeanDefinitionException {
		return mApplicationContext.getType(name);
	}

	public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
		return mApplicationContext.getAliases(name);
	}

}
