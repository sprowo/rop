package com.prowo.test.handle;

import com.prowo.common.rop.RopRequestBody;
import com.prowo.common.rop.RopResponse;
import com.prowo.test.request.RopHelloWorldRequest;
import com.prowo.test.response.RopHelloWorldResponse;

public interface IHelloWorld {
    public RopResponse<RopHelloWorldResponse> sayHello(RopRequestBody<RopHelloWorldRequest> request);
}
