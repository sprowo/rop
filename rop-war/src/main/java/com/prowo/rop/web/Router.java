package com.prowo.rop.web;

import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.ResultPath;

import com.prowo.common.rop.RopRequest;
import com.prowo.common.rop.RopResponse;
import com.prowo.common.util.FastJsonUtils;
import com.prowo.common.util.MD5;
import com.prowo.common.utils.StringUtil;
import com.prowo.common.utils.ZipUtils;
import com.prowo.rop.context.RopContext;
import com.prowo.rop.intercept.LogInterceptor;
import com.prowo.rop.memcached.MemcachedUtil;
import com.prowo.rop.utils.Representation;
import com.prowo.rop.utils.ServiceMethodHandler;

@ParentPackage("ropContextInterceptorPackage")
@ResultPath("/ropInterceptor")
public class Router extends BaseAction{
	private static final long serialVersionUID = 2815915261546802210L;

	private static final int DEFAULT_CACHE_SECENDS = 600;

	private final static Logger logger = Logger
			.getLogger(Router.class);
	private RopContext ropContext;
	private String method;
	@Action(value="/router/rest", interceptorRefs = {
			@InterceptorRef(value = "ropInterceptor"),
			@InterceptorRef(value = "defaultStack"),
			//@InterceptorRef(value = "preventSQLInjectInterceptor"),
			//@InterceptorRef(value = "forceUpgradeInterceptor"),
			@InterceptorRef(value = "clientDefaultStack"),
			//@InterceptorRef(value = "cacheInterceptor")
			})
	public void router() throws Exception{
		ServiceMethodHandler handler = ropContext.getServiceHandler();
		Object response =  handler.getHandlerMethod().invoke(handler.getHandler(), ropContext.getRequestData());

		if(response instanceof Representation){
			Representation re = (Representation)response;
			re.outPut(ropContext.getResponse());
			return;
		}
		//设置DEBUG信息
		LogInterceptor.setDebugMsg(ropContext.getRequest(), response);

		RopRequest baseRequest = (RopRequest)ropContext.getRequestData().getT();
		String dataStr = "";
		if(baseRequest!=null && baseRequest.isCheckVersion()){
			RopResponse<?> repResponse = (RopResponse<?>)response;
			Object jsonObjTargetStr = FastJsonUtils.toJson(repResponse.getData());
			String version = "";

			try {
				version = MD5.encode((String)jsonObjTargetStr);
				repResponse.setData(null);
				repResponse.setVersion(version);


			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 涓轰簡璺ㄥ煙璁块棶鑰屼慨姝�
		String callBack = baseRequest.getRequest().getParameter("callback");

		dataStr = FastJsonUtils.toJsonFilterProperties(response,baseRequest.getAppVersion(),baseRequest.isIpad()).toString();

		//浠庣紦瀛樹腑鍙�
		if(handler.isCached() && !baseRequest.isCheckVersion()){
			String cacheKey = baseRequest.getCacheKey();
			int cacheSecends = baseRequest.getCacheSecends() == 0 ? DEFAULT_CACHE_SECENDS : baseRequest.getCacheSecends();
			MemcachedUtil memcachedUtil = MemcachedUtil.getInstance();
			boolean cacheFlag = memcachedUtil.set(cacheKey, cacheSecends, ZipUtils.gzip(dataStr));
			logger.info(StringUtils.join(handler.getHandlerMethod(), (cacheFlag == true ? "GZIP娣诲姞缂撳瓨鎴愬姛" : "GZIP娣诲姞缂撳瓨澶辫触")));
		}

		// 璇锋眰绫诲瀷涓簀sonp鐨勫満鍚�
		if (StringUtil.isNotEmpty(callBack)) {
			dataStr = "jsonpCallBack(" + dataStr + ")";

		}

		this.sendAjaxResultByJson(dataStr);
	}

	public RopContext getRopContext() {
		return ropContext;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setRopContext(RopContext ropContext) {
		this.ropContext = ropContext;
	}
}
