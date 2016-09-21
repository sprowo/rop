package com.prowo.test.request;

import com.prowo.common.rop.RopRequest;
import com.prowo.common.util.ClientMemCacheConstants;
import org.apache.commons.lang3.StringUtils;

public class RopHelloWorldRequest extends RopRequest {

    private static final long serialVersionUID = 7683393066124643022L;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCacheKey() {
        return StringUtils.join(ClientMemCacheConstants.ANNOTATION_CACHEKEY_CONSTANTS.HELLO_WORLD.getCacheKey(), this.username);
    }

    public int getCacheSecends() {
        return ClientMemCacheConstants.ANNOTATION_CACHEKEY_CONSTANTS.HELLO_WORLD.getCacheSecends();
    }

    @Override
    public String toString() {
        return "RopHelloWorldRequest{" +
                "username='" + username + '\'' +
                '}';
    }
}
