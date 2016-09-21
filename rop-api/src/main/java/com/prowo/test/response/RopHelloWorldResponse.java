package com.prowo.test.response;

import java.io.Serializable;

public class RopHelloWorldResponse implements Serializable {

    private static final long serialVersionUID = 1723808150716676068L;

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "RopHelloWorldResponse{" +
                "content='" + content + '\'' +
                '}';
    }
}
