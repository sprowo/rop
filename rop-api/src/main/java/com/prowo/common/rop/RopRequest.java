package com.prowo.common.rop;

import com.prowo.common.util.InternetProtocol;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * 客户端请求参数基类
 */
public class RopRequest implements Serializable {

    private static final long serialVersionUID = -1519300204899324513L;
    private String method;
    private String version;
    private String apiVersion;
    private String token;
    private String lvsessionid;
    private String udid;
    private String osVersion;
    private String deviceName;
    private String firstChannel;
    private String secondChannel;
    private String lvversion;
    private boolean queryCount;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String ip;
    private String customerId;

    /**
     * 缓存动态key
     */
    private String cacheKey;

    /**
     * 缓存动态时间
     */
    private int cacheSecends;

    /**
     * 验证码
     */
    private String validateCode;

    /**
     * 用户ID
     **/
    private Long userId;

    /**
     * 用户编码
     **/
    private String userNo;

    /**
     * 是否是检查版本
     */
    private boolean checkVersion;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 经度
     */
    private String longitude;

    // @NotNull(message="方法名不能为空！")
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    // @NotNull(message="版本号不能为空！")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUdid() {
        return udid;
    }

    public void setUdid(String udid) {
        this.udid = udid;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getFirstChannel() {
        return firstChannel;
    }

    public void setFirstChannel(String firstChannel) {
        this.firstChannel = firstChannel;
    }

    public String getSecondChannel() {
        return secondChannel;
    }

    public void setSecondChannel(String secondChannel) {
        this.secondChannel = secondChannel;
    }

    public String getLvsessionid() {
        return lvsessionid;
    }

    public void setLvsessionid(String lvsessionid) {
        this.lvsessionid = lvsessionid;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public boolean isCheckVersion() {
        return checkVersion;
    }

    public void setCheckVersion(boolean checkVersion) {
        this.checkVersion = checkVersion;
    }

    public boolean isAnroid() {
        return "ANDROID".equals(firstChannel);
    }

    public boolean isIOS() {
        return "IPHONE".equals(firstChannel);
    }

    public boolean isIpad() {
        return "IPAD".equals(firstChannel);
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public String getIp() {
        if (!StringUtils.isEmpty(ip)) {
            return ip;
        } else {
            this.ip = InternetProtocol.getRemoteAddr(getRequest());
            return ip;
        }
    }

    public String getCachKey() {
        return "1";
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public String getCacheKey() {
        return cacheKey;
    }

    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }

    public int getCacheSecends() {
        return cacheSecends;
    }

    public void setCacheSecends(int cacheSecends) {
        this.cacheSecends = cacheSecends;
    }

    public String getLvversion() {
        return lvversion;
    }

    public void setLvversion(String lvversion) {
        this.lvversion = lvversion;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public int getAppVersion() {
        int appVersion = 0;
        if (StringUtils.isNotBlank(this.lvversion)) {
            try {
                appVersion = Integer.parseInt(this.lvversion.replace(".", ""));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return appVersion;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public boolean isQueryCount() {
        return queryCount;
    }

    public void setQueryCount(boolean queryCount) {
        this.queryCount = queryCount;
    }

    public String getValidateCodeCacheKey() {
        return "VALIDATE_RAND_CODE_PREFIX" + this.lvsessionid;
    }

    @Override
    public String toString() {
        return "RopRequest [method=" + method + ", version=" + version
                + ", apiVersion=" + apiVersion + ", token=" + token
                + ", lvsessionid=" + lvsessionid + ", udid=" + udid
                + ", osVersion=" + osVersion + ", deviceName=" + deviceName
                + ", firstChannel=" + firstChannel + ", secondChannel="
                + secondChannel + ", lvversion=" + lvversion + ", queryCount="
                + queryCount + ", request=" + request + ", response="
                + response + ", ip=" + ip + ", customerId=" + customerId
                + ", cacheKey=" + cacheKey + ", validateCode=" + validateCode
                + ", userId=" + userId + ", userNo=" + userNo
                + ", checkVersion=" + checkVersion + ", latitude=" + latitude
                + ", longitude=" + longitude + "]";
    }

}
