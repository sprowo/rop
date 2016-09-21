package com.prowo.rop.intercept;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.prowo.common.rop.RopRequest;
import com.prowo.common.utils.ZipUtils;
import com.prowo.rop.context.RopContext;
import com.prowo.rop.memcached.MemcachedUtil;
import com.prowo.rop.utils.ServiceMethodHandler;

/**
 * 类描述：缓存
 */
public class CacheInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 8674873127319932933L;
	private static final Log logger = LogFactory.getLog(CacheInterceptor.class);

	public String intercept(ActionInvocation invocation) throws Exception {
		try {
			RopContext ropContext = (RopContext) invocation
					.getInvocationContext().getParameters().get("ropContext");
			ServiceMethodHandler handler = ropContext.getServiceHandler();
			RopRequest baseRequest = (RopRequest) ropContext.getRequestData()
					.getT();
			if (handler.isCached() && baseRequest != null
					&& !baseRequest.isCheckVersion()) {
				String cacheKey = baseRequest.getCacheKey();
				if (StringUtils.isNotBlank(cacheKey)) {
					logger.info("从缓存中取�??...........");
					MemcachedUtil memcachedUtil = MemcachedUtil.getInstance();
					Object obj = memcachedUtil.get(cacheKey);
					if (obj != null) {
						String compressedStr = (String) obj;
						String dataStr = ZipUtils.gunzip(compressedStr);
						if (StringUtils.isNotBlank(dataStr)) {
							logger.info("get data from cache success.........");
							this.sendAjaxResultByJson(dataStr);
							return null;
						}
						logger.info("缓存失效，有key但没value.........");
					}
					logger.info("缓存失效............");
				}
			}
		} catch (Exception e) {
			logger.info("从缓存中取�?�失�?...........");
			return invocation.invoke();
		}
		return invocation.invoke();
	}

	/**
	 * 发�?�Ajax请求结果json
	 *
	 * @throws ServletException
	 * @throws IOException
	 */
	private void sendAjaxResultByJson(String json) {
		this.getResponse().setContentType("application/json;charset=UTF-8");
		this.getResponse().setCharacterEncoding("UTF-8");
		try {
			PrintWriter out = this.getResponse().getWriter();
			out.write(json);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

}
