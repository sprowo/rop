package com.prowo.common.util;


public class ClientMemCacheConstants {

    //1分钟
    private static final int CACHE_ONE_MINS = 60;
    //5分钟
    private static final int CACHE_FIVE_MINS = 5 * 60;
    //10分钟
    private static final int CACHE_TEN_MINS = 10 * 60;
    //15分钟
    private static final int CACHE_FIVTEEN_MINS = 15 * 60;
    //半小时
    private static final int CACHE_HALF_HOUR = 30 * 60;
    //一小时
    private static final int CACHE_ONE_HOURS = 1 * 60 * 60;
    //两小时
    private static final int CACHE_TOW_HOURS = 2 * 60 * 60;
    //一个星期
    private static final int CACHE_ONE_WEEK = 24 * 7 * 60 * 60;


    /**
     * ANNOTATION注解方式缓存key和secends
     * 组装格式：模块_（方法名+版本号）（cacheKey, cacheSecends�?
     * cacheKey只是前缀，后面可自己添加参数拼装
     */
    public static enum ANNOTATION_CACHEKEY_CONSTANTS {

        HELLO_WORLD("API_ANNOTATION_HELLO_WORLD_", CACHE_ONE_MINS);

        private String cacheKey;
        private int cacheSecends;

        private ANNOTATION_CACHEKEY_CONSTANTS(String cacheKey, int cacheSecends) {
            this.cacheKey = cacheKey;
            this.cacheSecends = cacheSecends;
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

    }

}
