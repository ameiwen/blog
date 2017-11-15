package com.blog.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public ContextUtil() {
    }

    public void setApplicationContext(ApplicationContext _applicationContext) {
        this.applicationContext = _applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        checkApplicationContext();
        return applicationContext;
    }

    public static <T> T getBean(String name) {
        checkApplicationContext();
        return (T) applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> requiredType) {
        checkApplicationContext();
        return applicationContext.getBean(requiredType);
    }

    public static void cleanApplicationContext() {
        applicationContext = null;
    }

    private static void checkApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextHolder");
        }
    }
}