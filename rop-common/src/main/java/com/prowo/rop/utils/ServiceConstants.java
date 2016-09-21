package com.prowo.rop.utils;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

public class ServiceConstants {
	public final static long DISTRIBUTOR_ID = 10000;
	public final static long LVYUE_DISTRIBUTOR_ID = 111;
	public final static long DISTRIBUTION_CHANNEL = 10000;
	public final static String DISTRIBUTOR_CODE = "LVTU";

	/** 驴途团购分销渠道ID **/
	public final static long DISTRIBUTOR_GROUPBUY_CHANNEL = 10001;
	/** 主站团购分销渠道ID **/
	public final static long DISTRIBUTOR_GROUPBUY_CHANNEL_WWW = 108;
	/** 驴途秒杀分销渠道ID **/
	public final static long DISTRIBUTOR_SECKILL_CHANNEL = 10002;
	/** 主站秒杀分销渠道ID **/
	public final static long DISTRIBUTOR_SECKILL_CHANNEL_WWW = 110;

	public static final String VALIDATE_CODE_PREFIX = "VALIDATE_CODE_PREFIX";
	public static final String VALIDATE_RAND_CODE_PREFIX = "VALIDATE_RAND_CODE_PREFIX";
	public static final String VALIDATE_CODE_DATE_PREFIX = "VALIDATE_CODE_DATE_PREFIX";
	public static final String SHARE_CONTENT = "推荐“驴妈妈旅游”的一家超高性价比的酒店";
	public static final String REFUND_NOT_ALLOW_TIPS = "该订单已超过最晚退款时间。如有需要请联系客服4001570570"; // 不能退款说明
	public static final String REFUND_NOT_ALLOW_TIPS_EIGHT = "该订单不退不改，抱歉无法申请退款";
	public static final String REFUND_NOT_ALLOW_TIPS_NINE = "该订单已超过退款时间，抱歉无法申请退款";
	public static final String REFUND_NOT_ALLOW_TIPS_TAOBAO = "该订单请联系淘宝客服申请退款";
	public static final String REFUND_NOT_ALLOW_TIPS_OTHER = "该订单请联系客服4001570570申请退款";
	public static final String REFUND_FROM = "无线客户端";

	/** 支付限额 **/
	public static final String CELLING_ALIPAY = "5000";
	public static final String CELLING_TENPAY = "8000";
	public static final String CELLING_UNIONPAY = "20000";
	public static final String CELLING_CCBPAY = "50000";
	public static final String CELLING_99BILLPAY = "50000";

	/**
	 * 缓存配置文件中的配置信息 ，是否需要图片验证
	 */
	private static final String NEW_VALIDATE_CODE = "config_client_need_validate_code_lv";

	/**
	 * 当前分钟内注册的用户数量
	 */
	public static final String CURRENT_MINUTE_REGISTER_KEY_PREFIX = "client_register_num_";
	/** 当前分钟内登录的用户数量 */
	public static final String CURRENT_MINUTE_LOGIN_KEY_PREFIX = "client_login_num_";

	private static Properties properties;
	private static volatile ServiceConstants instance = null;

	private void init() {
		try {
			properties = new Properties();
			properties
					.load(getClass().getResourceAsStream("/const.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// ResourceBundle.getBundle("const");
	}

	private ServiceConstants() {
		init();
	}

	public static ServiceConstants getInstance() {
		if (instance == null) {
			synchronized (ServiceConstants.class) {
				if (instance == null) {
					instance = new ServiceConstants();
					// instance.init();
				}
			}
		}
		return instance;
	}

	/**
	 * 获取属性
	 *
	 * @return
	 */
	public String getValue(String key) {
		return properties.getProperty(key);
	}

	public String getAliPayAppUrl() {
		return properties.getProperty("alipayAppUrl");
	}

	public String getAliPayWapUrl() {
		return properties.getProperty("alipayWapUrl");
	}

	public String getUpompPayUrl() {
		return properties.getProperty("upompUrl");
	}

	public String getTenpayWapUrl() {
		return properties.getProperty("tenpayWapUrl");
	}

	public String getBaiduWapUrl() {
		return properties.getProperty("baiduWapUrl");
	}

	public String getBaiduApppUrl() {
		return properties.getProperty("baiduAppUrl");
	}

	public String getWeixinAndroidUrl() {
		return properties.getProperty("weixinAndroid");
	}

	public String getWeixinIphoneUrl() {
		return properties.getProperty("weixinIphone");
	}

	public String getWeixinUnifiedOrderAndroidUrl() {
		return properties.getProperty("weixinUnifiedOrderAndroid");
	}

	public String getWeixinUnifiedOrderIOSUrl() {
		return properties.getProperty("weixinUnifiedOrderIOS");
	}

	public String getCcbUrl() {
		return properties.getProperty("ccbUrl");
	}

	public String getYeePayAppUrl() {
		return properties.getProperty("yeePayUrl");
	}

	public String getBill99PayUrl() {
		return properties.getProperty("99billPayUrl");
	}

	/**
	 * @Description: 判断客户端注册是否需要短信验证码
	 */
	public static boolean needImageAuthCode() {
		// String needImageCode = "true";
		// Long couter =
		// MemcachedUtil.getInstance().getCounter(NEW_VALIDATE_CODE);
		// if(couter==-1l){
		// needImageCode = properties.getProperty("client_need_image_code");
		// return "true".equals(needImageCode);
		// } else {
		// return couter>-1l;
		// }
		return false;
	}

	public static enum REFUND {
		FAIL("退款失败"), CANCEL("退款单取消"), REJECTED(" 不通过（拒绝、驳回）"), REFUNDED("退款成功"), PROCESSING(
				"银行处理中"), PROCESSING_PART("部分退款处理中"), REFUNDED_PART("部分退款完成"), VERIFIEDING(
				"退款审核中");
		private String cnName;

		REFUND(String name) {
			this.cnName = name;
		}

		public String getCode() {
			return this.name();
		}

		public String getCnName() {
			return this.cnName;
		}

		public static String getCnName(String code) {
			for (REFUND item : REFUND.values()) {
				if (item.getCode().equals(code)) {
					return item.getCnName();
				}
			}
			return code;
		}
	}

	public static enum VERSIONS {
		ALL("0", "所有版本");
		private String cnName;
		private int versionNumber;

		VERSIONS(String number, String cnName) {
			this.cnName = cnName;
			this.versionNumber = Integer.parseInt(number);
		}

		public String getCode() {
			return this.name();
		}

		public int getVersionNumber() {
			return this.versionNumber;
		}

		public String getCnName() {
			return this.cnName;
		}
	}

	public static enum ACCESS_ROLE {
		internal("内网");
		private String code;
		private String cnName;

		ACCESS_ROLE(String cnName) {
			this.cnName = cnName;
		}

		public String getCode() {
			return this.name();
		}

		public String getCnName() {
			return this.cnName;
		}

		public static String getCnName(String code) {
			for (ACCESS_ROLE item : ACCESS_ROLE.values()) {
				if (item.name().equals(code)) {
					return item.getCnName();
				}
			}
			return code;
		}
	}

	public static enum SALE_CHANNEL {

		groupbuy, // 销售渠道-团购
		seckill; // 销售渠道-秒杀
	}

	// 压缩图片的接口地址
	public String getCompressImageUrl() {
		String value = getValue("compressImageUrl");
		if (StringUtils.isNotBlank(value)) {
			return value;
		}
		return "";
	}

	public String getPicServerUrl() {

		String value = getValue("picServerUrl");
		if (StringUtils.isNotBlank(value)) {
			return value;
		}
		return "";
	}
}
