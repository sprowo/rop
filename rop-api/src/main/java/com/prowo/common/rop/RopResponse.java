package com.prowo.common.rop;

import com.alibaba.fastjson.annotation.JSONField;
import com.prowo.common.util.Constant;
import com.prowo.common.util.FastJsonUtils;
import com.prowo.common.util.MD5;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

public class RopResponse<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6939909168205094440L;

    String code = "";
    String message = "";
    String errorMessage = "";
    String version;
    String debugMsg;

    private T data;

    public RopResponse() {

    }

    public RopResponse(String code, String message, String errorMessage) {
        // TODO Auto-generated constructor stub
        this.code = code;
        this.message = message;
        this.errorMessage = errorMessage;
    }


    //	public String toJson(){
//		return "";
//	}
    public String toString() {
        return "";
    }


    public T getData() {
        return data;
    }


    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public String getErrorMessage() {
        return errorMessage;
    }


    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public String getVersion() {
        if (getData() != null) {

            Object jsonObjTargetStr = FastJsonUtils.toJson(getData());
            String version = "";
            try {
                version = MD5.encode(jsonObjTargetStr.toString());
                this.version = version;
            } catch (NoSuchAlgorithmException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return version;
    }

    @JSONField(serialize = false)
    public boolean isOk() {
        return Constant.CLIENT_ERROR_CODE.OK.getCnName().equals(code);
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDebugMsg() {
        return debugMsg;
    }

    public void setDebugMsg(String debugMsg) {
        this.debugMsg = debugMsg;
    }
}
