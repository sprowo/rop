package com.prowo.test;

import com.caucho.hessian.client.HessianProxyFactory;
import com.prowo.common.rop.RopRequestBody;
import com.prowo.common.rop.RopResponse;
import com.prowo.common.utils.HttpUtils;
import com.prowo.test.handle.IHelloWorld;
import com.prowo.test.request.RopHelloWorldRequest;
import com.prowo.test.response.RopHelloWorldResponse;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class HelloWorldTest {

	@Test
	public void sayHelloTest() {
		String url = "http://localhost:8080/rop-war/router/rest.do";

		Map<String, String> requestMap = new HashMap<String, String>();
		requestMap.put("method", "api.com.test.sayHello");
		requestMap.put("version", "1.0.0");
		requestMap.put("username", "linan");

		String result = HttpUtils.post(url, requestMap);
		System.out.println(result);
	}

	@Test
	public void sayHelloTest2() throws MalformedURLException {
		String url = "http://localhost:8080/rop-war/remote/helloWorldHandler";

		HessianProxyFactory factory = new HessianProxyFactory();
		IHelloWorld hello = (IHelloWorld) factory.create(IHelloWorld.class, url);

		RopHelloWorldRequest requestBean = new RopHelloWorldRequest();
		requestBean.setUsername("linan");
		requestBean.setApiVersion("1.0.0");
		requestBean.setMethod("api.com.test.sayHello");

		RopRequestBody<RopHelloWorldRequest> request = new RopRequestBody<RopHelloWorldRequest>();
		request.setT(requestBean);

		RopResponse<RopHelloWorldResponse> response = hello.sayHello(request);
		System.out.println(response.getData().toString());

	}

	public static void main2() throws MalformedURLException {
		// String url =
		// "http://10.43.1.138:9990/new-points-war/remote/helloWorldImpl";
		HessianProxyFactory factory = new HessianProxyFactory();
		// IHelloWorld hello =
		// (IHelloWorld)factory.create(IHelloWorld.class,url);
		// RopHelloWorldRequest requestBean = new RopHelloWorldRequest();
		// RopRequestBody<RopHelloWorldRequest> request = new
		// RopRequestBody<RopHelloWorldRequest>();
		// requestBean.setUsername("linan");
		// request.setT(requestBean);

		// RopResponse<RopResponseHelloWorld> response =hello.sayHello(request);
		// System.out.println(response.getData().getContent());

		// HessianProxyFactory factory = new HessianProxyFactory();
		// //String url1 =
		// "http://10.43.1.138:9990/new-points-war/remote/queryPointsServiceImpl";
		// String url1 =
		// "http://localhost:8080/new-points-war/remote/queryPointsServiceImpl";
		// IQueryPointsService queryPointsService =
		// (IQueryPointsService)factory.create(IQueryPointsService.class,url1);
		// QueryPointsRopRequest requestBean1 = new QueryPointsRopRequest();
		// RopRequestBody<QueryPointsRopRequest> request1 = new
		// RopRequestBody<QueryPointsRopRequest>();
		// request1.setT(requestBean1);
		// RopResponse<QueryPointsRopResponse> response1 =
		// queryPointsService.queryPoints(request1);
		// System.out.println(response1.getData().toString());
	}

}
