package com.hzjd.redisdemo.util;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

@Component("myKeyGenerator")
/**
 * 去掉SimpleKey []+自定义
 * @author Sunly
 *
 */
public class MyKeyGenerator implements KeyGenerator {

	@Override
	public Object generate(Object target, Method method, Object... params) {
		return method.getName();
	}

}
