package com.prowo.rop.context;

import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.MethodCallback;
import org.springframework.util.ReflectionUtils.MethodFilter;

import com.prowo.rop.annotation.ApiMethod;
import com.prowo.rop.exception.RedefinedException;
import com.prowo.rop.exception.RopException;
import com.prowo.rop.utils.ServiceConstants;
import com.prowo.rop.utils.ServiceMethodHandler;

@Component
public class RopServiceHandler implements ApplicationContextAware,
		InitializingBean {
	private static final Logger log = Logger.getLogger(RopServiceHandler.class);
	private ApplicationContext applicationContext;

	// void addServiceMethod(String method,String version,String beanName);
	private static Map<String, Map<String, ServiceMethodHandler>> methodHandlerPool;

	public RopServiceHandler() {
		methodHandlerPool = new HashMap<String, Map<String, ServiceMethodHandler>>();
	}

	public void addHandlerMathod(String method, String version,
			ServiceMethodHandler handler) {

		Map<String, ServiceMethodHandler> map = methodHandlerPool.get(method);
		if (map == null) {
			map = new HashMap<String, ServiceMethodHandler>();
		}

		ServiceMethodHandler targetHandler = map.get(version);

		if (targetHandler != null) {
			log.error("重复定义方法和属性:" + targetHandler.getVersion() + "|"
					+ targetHandler.getClass().getName() + "|"
					+ targetHandler.getHandlerMethod().getName());
			throw new RedefinedException();
		}
		map.put(version, handler);
		methodHandlerPool.put(method, map);
	}

	public ServiceMethodHandler getHandler(String method, String version) {
		ServiceMethodHandler h = null;

		if (version == null) {
			version = ServiceConstants.VERSIONS.ALL.name();
		}

		Map<String, ServiceMethodHandler> map = methodHandlerPool.get(method);

		if (map == null) {
			throw new RopException(method + " 未找到");
		}

		h = map.get(version);

		if (h == null) {
			throw new RopException(method
					+ MessageFormat.format(" 对应{0}版本 未找到", h));
		}

		return h;
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	public void afterPropertiesSet() throws Exception {
		// 初始化 applicationContext6
		try {
			String[] beanNames = this.applicationContext
					.getBeanNamesForType(Object.class);
			for (final String beanName : beanNames) {
				Class<?> handlerType = this.applicationContext
						.getType(beanName);
				ReflectionUtils.doWithMethods(handlerType,
						new MethodCallback() {
							public void doWith(Method method)
									throws IllegalArgumentException,
									IllegalAccessException {
								ApiMethod apimethod = method
										.getAnnotation(ApiMethod.class);
								if (apimethod != null) {
									/**
									 * 检查aop 拦截器初始化
									 */
									if (apimethod.InterceptorRef().length > 0) {
										for (String interceptor : apimethod
												.InterceptorRef()) {
											Object aopInterceptor = applicationContext
													.getBean(interceptor);
										}
									}

									ServiceMethodHandler serviceMethodHandler = new ServiceMethodHandler();
									serviceMethodHandler
											.setHandler(applicationContext
													.getBean(beanName)); // handler
									serviceMethodHandler
											.setHandlerMethod(method); // handler'method
									serviceMethodHandler.setCached(apimethod
											.cached());
									log.info("初始化 服务方法：" + apimethod.method()
											+ "| 版本号：" + apimethod.version());
									addHandlerMathod(apimethod.method(),
											apimethod.version(),
											serviceMethodHandler);
								}
							}
						}, new MethodFilter() {

							public boolean matches(Method method) {
								return AnnotationUtils.findAnnotation(method,
										ApiMethod.class) != null;
							}
						});
			}

			log.info("共 初始化 " + methodHandlerPool.size() + " 个服务方法");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
