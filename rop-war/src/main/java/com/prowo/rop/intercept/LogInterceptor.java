package com.prowo.rop.intercept;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.prowo.common.rop.RopResponse;
import com.prowo.rop.log.LogStack;
import com.prowo.rop.log.LogUtils;

public class LogInterceptor extends AbstractInterceptor {

	public static final String IS_DEBUG = "IS_DEBUG";

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (!isDebug(request)) {
			return invocation.invoke();
		}

		String method = request.getParameter("method");
		String version = request.getParameter("version");
		if (StringUtils.isEmpty(method) || StringUtils.isEmpty(version)) {
			return invocation.invoke();
		}

		request.setAttribute(LogUtils.DEBUG_LOG,
				LogUtils.initLog(method + ' ' + version));
		return invocation.invoke();
	}

	/**
	 * 是否是BUG模式
	 *
	 * @param request
	 *            请求
	 * @return 是否是BUG模式
	 */
	public static boolean isDebug(HttpServletRequest request) {
		String isDebug = request.getParameter(IS_DEBUG);
		return "1".equals(isDebug);
	}

	/**
	 * 在response中设置debug的信息
	 *
	 * @param request
	 *            http请求
	 * @param response
	 *            结构化response
	 */
	public static void setDebugMsg(HttpServletRequest request, Object response) {
		if (request == null || response == null
				|| !(response instanceof RopResponse)) {
			return;
		}
		if (isDebug(request)) {
			RopResponse<?> ropResponse = (RopResponse<?>) response;
			LogStack root = LogUtils.getRootLog();
			if (root == null)
				return;
			LogUtils.endLog(root);
			ropResponse.setDebugMsg(root.toString());
		}
	}
}
