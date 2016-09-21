package com.prowo.rop.utils;

import java.lang.reflect.Method;

/**
 * 处理请求的服务相关信息
 *
 */
public class ServiceMethodHandler {
	// 处理器对象
	private Object handler;

	// 处理器的处理方法
	private Method handlerMethod;

	private String version;
	/**
	 * 是否缓存结果集
	 */
	private boolean cached;

	public boolean isCached() {
		return cached;
	}

	public void setCached(boolean cached) {
		this.cached = cached;
	}

	public Object getHandler() {
		return handler;
	}

	public void setHandler(Object handler) {
		this.handler = handler;
	}

	public Method getHandlerMethod() {
		return handlerMethod;
	}

	public void setHandlerMethod(Method handlerMethod) {
		this.handlerMethod = handlerMethod;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
