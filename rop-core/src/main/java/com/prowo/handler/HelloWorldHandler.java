package com.prowo.handler;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.prowo.common.rop.RopRequestBody;
import com.prowo.common.rop.RopResponse;
import com.prowo.rop.annotation.ApiMethod;
import com.prowo.rop.log.LogUtils;
import com.prowo.test.handle.IHelloWorld;
import com.prowo.test.request.RopHelloWorldRequest;
import com.prowo.test.response.RopHelloWorldResponse;

@Component
public class HelloWorldHandler implements IHelloWorld {
	private final Logger logger = Logger.getLogger(this.getClass());

	@Override
	@ApiMethod(method = "api.com.test.sayHello", version = "1.0.0", cached = false)
	public RopResponse<RopHelloWorldResponse> sayHello(
			RopRequestBody<RopHelloWorldRequest> request) {
		logger.info("start sayHello api, request parms: " + request.getT().toString());

		RopResponse<RopHelloWorldResponse> response = new RopResponse<RopHelloWorldResponse>();
		RopHelloWorldResponse responseData = new RopHelloWorldResponse();
		RopHelloWorldRequest helloWorld = request.getT();
		LogUtils.startLogWeb("测试api的debug日志功能");
		LogUtils.appendLogWeb("测试debug日志");
		responseData.setContent("hello ! " + helloWorld.getUsername());
		response.setData(responseData);
		LogUtils.endLogWeb();

		logger.info("end sayHello api, response parms: " + responseData.toString());
		return response;
	}

}
