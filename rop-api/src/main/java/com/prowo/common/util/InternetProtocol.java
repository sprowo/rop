
package com.prowo.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public final class InternetProtocol {
    private static Logger log = Logger.getLogger(InternetProtocol.class);

    /**
     * 构造函数.
     */
    private InternetProtocol() {
    }

    /**
     * 获取客户端IP地址
     * 支持多级反向代理
     *
     * @param request HttpServletRequest
     * @return 客户端真实IP地址
     */
    public static String getRemoteAddr(final HttpServletRequest request) {
        try {
            String remoteAddr = request.getHeader("X-Forwarded-For");
            // 如果通过多级反向代理，X-Forwarded-For的值不止一个，而是一串用逗号分隔的IP值，此时取X-Forwarded-For中第一个非unknown的有效IP字符串
            if (isEffective(remoteAddr) && (remoteAddr.indexOf(",") > -1)) {
                String[] array = remoteAddr.split(",");
                for (String element : array) {
                    if (isEffective(element)) {
                        remoteAddr = element;
                        break;
                    }
                }
            }
            if (!isEffective(remoteAddr)) {
                remoteAddr = request.getHeader("X-Real-IP");
            }
            if (!isEffective(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
            return remoteAddr;
        } catch (Exception e) {
            log.error("get romote ip error,error message:" + e.getMessage());
            return "";
        }
    }

    /**
     * 获取客户端源端口
     *
     * @param request
     * @return
     */
    public static Long getRemotePort(final HttpServletRequest request) {
        try {
            String port = request.getHeader("remote-port");
            if (StringUtils.isNotBlank(port)) {
                try {
                    return Long.parseLong(port);
                } catch (NumberFormatException ex) {
                    log.error("convert port to long error , port:	" + port);
                    return 0l;
                }
            } else {
                return 0l;
            }
        } catch (Exception e) {
            log.error("get romote port error,error message:" + e.getMessage());
            return 0l;
        }
    }

    /**
     * 远程地址是否有效.
     *
     * @param remoteAddr 远程地址
     * @return true代表远程地址有效，false代表远程地址无效
     */
    private static boolean isEffective(final String remoteAddr) {
        boolean isEffective = false;
        if ((null != remoteAddr) && (!"".equals(remoteAddr.trim()))
                && (!"unknown".equalsIgnoreCase(remoteAddr.trim()))) {
            isEffective = true;
        }
        return isEffective;
    }

//	/**
//	 *  判断是否是内网IP 
//	 *  
//	 *  */
//	public static boolean isInnerIP(String ipAddress) {
//		boolean isInnerIp = false;
//		long ipNum = IPMap.stringToLong(ipAddress);
//		/**
//		 * 私有IP：A类 10.0.0.0-10.255.255.255 B类 172.16.0.0-172.31.255.255 C类
//		 * 192.168.0.0-192.168.255.255 当然，还有127这个网段是环回地址
//		 **/
//		long aBegin = IPMap.stringToLong("10.0.0.0");
//		long aEnd = IPMap.stringToLong("10.255.255.255");
//		long bBegin = IPMap.stringToLong("172.16.0.0");
//		long bEnd = IPMap.stringToLong("172.31.255.255");
//		long cBegin = IPMap.stringToLong("192.168.0.0");
//		long cEnd = IPMap.stringToLong("192.168.255.255");
//		isInnerIp = isInner(ipNum, aBegin, aEnd) || isInner(ipNum, bBegin, bEnd) || isInner(ipNum, cBegin, cEnd) || ipAddress.equals("127.0.0.1");
//		return isInnerIp;
//	}

//	/**
//	 * 限制公司对外IP 公司IP返回true
//	 * @param ipAddress
//	 * @return
//	 */
//	public static boolean isInnerIP2(String ipAddress) {
//		boolean isInnerIp = false;
//		long ipNum = IPMap.stringToLong(ipAddress);
//		long aBegin = IPMap.stringToLong("180.169.51.82");
//		long aEnd = IPMap.stringToLong("180.169.51.94");
//		long bBegin = IPMap.stringToLong("27.115.86.218");
//		long bEnd = IPMap.stringToLong("27.115.86.222");
//		long cBegin = IPMap.stringToLong("222.66.131.98");
//		long cEnd = IPMap.stringToLong("222.66.131.98");
//		long dBegin = IPMap.stringToLong("101.231.197.30");
//		long dEnd = IPMap.stringToLong("101.231.197.30");
//		isInnerIp = isInner(ipNum, aBegin, aEnd) || isInner(ipNum, bBegin, bEnd) || isInner(ipNum, cBegin, cEnd) || isInner(ipNum,dBegin,dEnd) || ipAddress.equals("127.0.0.1");
//		return isInnerIp;
//	}


    private static boolean isInner(long userIp, long begin, long end) {
        return (userIp >= begin) && (userIp <= end);
    }
}
