package com.prowo.common.util;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Constant {

    private static Properties properties;
    private static volatile Constant instance = null;

    public static final String HOTELCASHBACKDESC = "奖金返现可能会有0-7个工作日左右延时，是由于和酒店确认需要时间，请谅解";
    public static final String SHARE_CONTENT = "自助游天下，就找驴妈妈-“%s”，%s折起预订";
    public static final String TICKET_WEIXIN_SHARE_TITLE = "%s_驴妈妈旅游";
    public static final String TICKET_WEIXIN_SHARE_CONTENT = "%s_驴妈妈旅游";
    //	public static final String TICKET_WEIXIN_SHARE_CONTENT = "这个门票很不错，值得推荐给你！";
    public static final String TICKET_WEIBO_SHARE_CONTENT = "%s门票，驴妈妈手机预订享更多返现，快去试试！%s";
    public static final String COMPRESS_PIC_SIZE = "580x290";
    public static final String COMMENT_ACTIVITY_PRDFIX = "【点评有奖第6季】";
    public static final String WARM_PRESENTATION = "温馨提示\n" +
            "1.在不能满足设备安全运行的恶劣天气，无法抗拒的自然灾害情况下（如雷电、雨雪、冰雹、大雾、暴雨、台风等），景区部分项目将临时关闭或部分关闭，表演会取消或部分取消。 \n\n" +
            "2.严禁倒票，一经发现，相关电子门票作废，入园现场将不予承认，驴妈妈旅游网将不予退费并将相关账号、手机号列入黑名单。请广大用户选择驴妈妈官方网站或官方合作渠道购买，维护自身权益不被侵犯。\n\n" +
            "3.景区活动内容如有变动，以当日公告为准。景区所有项目均会定期进行日检、周检、月检和年度检修，相关检修工作可能会造成部分项目运行时间的延迟或暂停对游客开放，详情以景区现场公告为准。\n\n" +
            "4.如需开具旅游发票(仅限支付给驴妈妈的订单用户)，请与客服专员确定发票内容与抬头及准确的发票邮寄地址，我司在收到邮寄地址信息后向您寄送发票；为避免因发生不可抗力或意外事项致实际消费额与发票开具的相应数额不符，建议您在游玩归来后两个月内索领取发票。具体特殊景区请以产品说明为准。\n\n" +
            "5.为保障您的出游安全，驴妈妈建议您订购在线支付产品时选购相关旅游保险。";

    private void init() {
        try {
            properties = new Properties();
            properties.load(getClass().getResourceAsStream("/const.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // ResourceBundle.getBundle("const");
    }

    private Constant() {
        init();
    }

    public static Constant getInstance() {
        if (instance == null) {
            synchronized (Constant.class) {
                if (instance == null) {
                    instance = new Constant();
                    //instance.init();
                }
            }
        }
        return instance;
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * 手机端预定门票多返奖金
     * 单位 ： 分
     *
     * @return rul
     */
    public static Long getMobileTiketRefund() {
        String refund = getProperty("mobile.tiket.refund");
        if (!StringUtils.isEmpty(refund)) {
            return Long.valueOf(refund);
        }
        return 0l;
    }

    public static enum CLIENT_PRODCUT_TYPE {
        TUANGOU("团购"),
        SECKILL("秒杀");
        private String cnName;

        CLIENT_PRODCUT_TYPE(String name) {
            this.cnName = name;
        }

        public String getCode() {
            return this.name();
        }

        public String getCnName() {
            return this.cnName;
        }

        public static String getCnName(String code) {
            for (CLIENT_FAVORITE_TYPE item : CLIENT_FAVORITE_TYPE.values()) {
                if (item.getCode().equals(code)) {
                    return item.getCnName();
                }
            }
            return code;
        }

        public String toString() {
            return "code:" + this.name() + ",cnName:" + this.cnName;
        }
    }

    public static enum CLIENT_FAVORITE_TYPE {
        PLACE("标的"),
        PRODUCT("产品"),
        GUIDE("攻略"),
        TUANGOU("团购"),
        SHIP("邮轮"),
        HOTEL("酒店"),
        SECKILL("秒杀"),
        VISA("签证");
        private String cnName;

        CLIENT_FAVORITE_TYPE(String name) {
            this.cnName = name;
        }

        public String getCode() {
            return this.name();
        }

        public String getCnName() {
            return this.cnName;
        }

        public static String getCnName(String code) {
            for (CLIENT_FAVORITE_TYPE item : CLIENT_FAVORITE_TYPE.values()) {
                if (item.getCode().equals(code)) {
                    return item.getCnName();
                }
            }
            return code;
        }

        public String toString() {
            return "code:" + this.name() + ",cnName:" + this.cnName;
        }
    }

    /**
     * 手机端预定线路多返奖金
     * 单位 ： 分
     *
     * @return rul
     */
    public static Long getMobileRouteRefund() {
        String refund = getProperty("mobile.route.refund");
        if (!StringUtils.isEmpty(refund)) {
            return Long.valueOf(refund);
        }
        return 0l;
    }

    /**
     * 支付网关
     *
     * @author ZHANG Nan
     */
    public static enum PAYMENT_GATEWAY {
        ALIPAY_CLIENT("支付宝客户度"),
        ALIPAY("支付宝"),
        @Deprecated
        CHINAPAY("中国银联"),
        UNIONPAY("银联"),
        CHINAPAY_PRE("银联预授权"),
        CASH_ACCOUNT("现金账户"),
        CASH_BONUS("奖金账户"),
        @Deprecated
        ONE_CITY_ONE_CARD("新华一卡通"),
        CMB("招商银行"),
        SPDB("上海浦东发展银行"),
        CMB_INSTALMENT("招商银行分期"),
        COMM("交通银行"),
        COMM_POS("交通银行POS机"),
        COMM_POS_CASH("交通银行POS机现金支付"),
        SAND_POS("杉德POS机"),
        SAND_POS_CASH("杉德POS机现金支付"),
        BOC("中国银行"),
        @Deprecated
        SHENGPAY("盛付通"),
        PAY_0_YUAN("订单金额为0"),
        STORED_CARD("储值卡"),
        LYTXK_STORED_CARD("驴游天下卡"),
        BOC_INSTALMENT("中国银行分期"),
        UPOMP("手机端银联支付"),
        ALIPAY_DIRECTPAY("支付宝快捷"),
        ALIPAY_WAP("支付宝手机WAP支付"),
        ALIPAY_WAP_CREDITCARD("支付宝手机WAP信用卡支付"),
        ALIPAY_WAP_DEBITCARD("支付宝手机WAP储值卡支付"),
        ALIPAY_APP("支付宝手机APP支付"),
        CHINAPNR("汇付天下"),
        @Deprecated
        ALLINPAY("通联支付"),
        TELBYPAY("百付电话 支付"),
        ALIPAY_OFFLINE("淘宝订单"),
        LAKALA("拉卡拉支付"),
        ALIPAY_BPTB("支付宝批量付款到银行"),
        ALIPAY_BATCH("支付宝批量付款到支付宝帐号"),
        CHINA_MOBILE_PAY("中国移动支付"),
        NING_BO_BANK("宁波银行"),
        ICBC("工商银行"),
        ICCB("建设银行"),
        TENPAY("财付通"),
        TENPAY_APP("财付通手机APP支付"),
        TENPAY_WAP("财付通手机WAP支付"),
        WEIXIN_WEB("财付通微信WEB扫码支付"),
        WEIXIN_IOS("财付通微信IOS支付"),
        WEIXIN_ANDROID("财付通微信android支付"),
        BAIDUPAY_APP("百度钱包支付"),
        BAIDUPAY_WAP("百度钱包支付"),
        BAIDUPAY_APP_ACTIVITIES("百度钱包APP手机支付活动"),
        BAIDUPAY_WAP_ACTIVITIES("百度钱包WAP手机支付活动"),
        YEEPAY("预授权易宝支付"),
        BILL99PAY("快钱支付"),
        WEIXIN_APP("微信支付"),
        ICBC_INSTALMENT("工商银行分期");


        private String cnName;

        PAYMENT_GATEWAY(String name) {
            this.cnName = name;
        }

        public String getCode() {
            return this.name();
        }

        public String getCnName() {
            return this.cnName;
        }

        public static String getCnName(String code) {
            for (PAYMENT_GATEWAY item : PAYMENT_GATEWAY.values()) {
                if (item.getCode().equals(code)) {
                    return item.getCnName();
                }
            }
            return code;
        }
    }

    public static enum CLIENT_ERROR_CODE {
        WARNING("2"),//警告，客户端对该错误码做特殊弹窗处理
        OK("1"),//提示errorMessage
        ERROR("-1"),//提示Message
        NO_PRODUCT("-2"),//无产品或者产品不可售
        NEED_VLIDATE_CODE("-5");//验证参数失败
        private String cnName;

        CLIENT_ERROR_CODE(String name) {
            this.cnName = name;
        }

        public String getCode() {
            return this.name();
        }

        public String getCnName() {
            return this.cnName;
        }

        public static String getCodeName(String code) {
            for (CLIENT_ERROR_CODE item : CLIENT_ERROR_CODE.values()) {
                if (item.getCode().equals(code)) {
                    return item.getCnName();
                }
            }
            return code;
        }

        public String toString() {
            return this.name();
        }
    }

    public static enum ORDER_STATUS {
        NORMAL("正常"),
        COMPLETE("已完成"),
        CANCEL("已取消");

        private String cnName;

        ORDER_STATUS(String name) {
            this.cnName = name;
        }

        public static String getCnName(String code) {
            for (ORDER_STATUS item : ORDER_STATUS.values()) {
                if (item.getCode().equals(code)) {
                    return item.cnName;
                }
            }
            return code;
        }

        public static ORDER_STATUS getValueByCode(String code) {
            ORDER_STATUS ret = null;
            for (ORDER_STATUS item : ORDER_STATUS.values()) {
                if (item.name().equals(code)) {
                    ret = item;
                }
            }

            return ret;
        }

        public String getCode() {
            return this.name();
        }

        public String getCnName() {
            return this.cnName;
        }

        @Override
        public String toString() {
            return this.name();
        }

    }

    public static enum ORDER_VIEW_STATUS {
        UNVERIFIED("待审核"),
        PAYED("已支付"),
        WAIT_PAY("待支付 "),
        APPROVING("审核中"),
        CANCEL("取消"),
        COMPLETE("完成 ");

        private String cnName;

        ORDER_VIEW_STATUS(String name) {
            this.cnName = name;
        }

        public static String getCnName(String code) {
            for (ORDER_VIEW_STATUS item : ORDER_VIEW_STATUS.values()) {
                if (item.getCode().equals(code)) {
                    return item.cnName;
                }
            }
            return code;
        }

        public String getCode() {
            return this.name();
        }

        public String getCnName() {
            return this.cnName;
        }

        @Override
        public String toString() {
            return this.name();
        }
    }

    public static enum PAYMENT_STATUS {
        UNPAY("待支付"), /**
         * 待支付
         **/
        PART_PAY("部分支付"), /**
         * 部分支付
         **/
        PAYED("已支付 ");
        /**
         * 已支付
         **/

        private String cnName;

        PAYMENT_STATUS(String name) {
            this.cnName = name;
        }

        public static String getCnName(String code) {
            for (PAYMENT_STATUS item : PAYMENT_STATUS.values()) {
                if (item.getCode().equals(code)) {
                    return item.cnName;
                }
            }
            return code;
        }

        public String getCode() {
            return this.name();
        }

        public String getCnName() {
            return this.cnName;
        }

        @Override
        public String toString() {
            return this.name();
        }
    }

    public static enum CANCELSTRATEGY {
        RETREATANDCHANGE("可退改"),//可退改
        UNRETREATANDCHANGE("不退不改");//不退不改
        private String cnName;

        public static String getCnName(String code) {
            for (CANCELSTRATEGY item : CANCELSTRATEGY.values()) {
                if (item.getCode().equals(code)) {
                    return item.getCnName();
                }
            }
            return code;
        }

        CANCELSTRATEGY(String name) {
            this.cnName = name;
        }

        public String getCode() {
            return this.name();
        }

        public String getCnName() {
            return this.cnName;
        }

        @Override
        public String toString() {
            return this.name();
        }
    }

    public static enum SUB_PRODUCT_TYPE {
        /**
         * 单票，单一门票
         */
        SINGLE("单票"),
        /**
         * 套票，同一景区多人票组合
         */
        SUIT("套票"),
        /**
         * 联票，不同景区组合
         */
        UNION("联票"),
        /**
         * 通票，同一景区所有项目组合
         */
        WHOLE("通票"),
        /**
         * 境内自由行,单人出发
         */
        FREENESS("目的地自由行"),
        /**
         * 境内跟团游,多人成团
         */
        GROUP("短途跟团游"),
        /**
         * 境外自由行,单人出发
         */
        FREENESS_FOREIGN("出境自由行"),
        /**
         * 境外跟团游,多人成团
         */
        GROUP_FOREIGN("出境跟团游"),
        /**
         * 保险
         */
        INSURANCE("保险"),
        /**
         * 单房间
         */
        SINGLE_ROOM("单房间 "),
        /**
         * 酒店套餐
         */
        HOTEL_SUIT("酒店套餐"),
        /**
         * 境外酒店
         */
        HOTEL_FOREIGN("境外酒店"),
        /**
         * 长途跟团游
         */
        GROUP_LONG("长途跟团游"),
        /**
         * 长途自由行
         */
        FREENESS_LONG("长途自由行"),
        /**
         * 自助巴士班
         */
        SELFHELP_BUS("自助巴士班"),
        /**
         * 签证
         **/
        VISA("签证"),
        /**
         * 其它
         */
        OTHER("其它"),
        /**
         * 自费
         */
        OWNEXPENSE("自费"),
        /**
         * 港务税
         */
        PORTTAX("港务税"),
        /**
         * 税金
         **/
        TAX("税金"),
        /**
         * 快递费
         **/
        EXPRESS("快递费"),
        /**
         * 房差
         */
        FANGCHA("房差"),
        /**
         * 小费
         */
        TIP("小费"),
        /**
         * 机票
         */
        FLIGHT("机票"),
        /**
         * 火车票
         **/
        TRAIN("火车票"),
        /**
         * 巴士
         */
        BUS("巴士");

        private String cnName;

        SUB_PRODUCT_TYPE(String name) {
            this.cnName = name;
        }

        public String getCode() {
            return this.name();
        }

        public String getCnName() {
            return this.cnName;
        }

        public static String getCnName(String code) {
            for (SUB_PRODUCT_TYPE item : SUB_PRODUCT_TYPE.values()) {
                if (item.getCode().equals(code)) {
                    return item.getCnName();
                }
            }
            return code;
        }


        public String toString() {
            return this.name();
        }
//		public static List<PageElementModel> getList(){
//			List<PageElementModel> list=new ArrayList<PageElementModel>();
//			PageElementModel sm=null;
//			for(SUB_PRODUCT_TYPE item:SUB_PRODUCT_TYPE.values()){
//				sm=new PageElementModel();
//				sm.setElementCode(item.getCode());
//				sm.setElementValue(item.getCnName());
//				list.add(sm);
//			}
//			return list;
//		}
    }

    public static enum PRODUCT_TYPE {
        /**
         * 大交通
         **/
        TRAFFIC("大交通"),
        /**
         * 门票
         */
        TICKET("门票"),
        /**
         * 酒店
         */
        HOTEL("酒店"),
        /**
         * 境外酒店
         */
        HOTEL_FOREIGN("境外酒店"),
        /**
         * 线路
         */
        ROUTE("线路"),
        /**
         * 游轮
         */
        CRUISE("邮轮"),
        /**
         * 其它
         */
        OTHER("其它");

        private String cnName;

        PRODUCT_TYPE(String name) {
            this.cnName = name;
        }

        public String getCode() {
            return this.name();
        }

        public String getCnName() {
            return this.cnName;
        }

        public static String getCnName(String code) {
            for (PRODUCT_TYPE item : PRODUCT_TYPE.values()) {
                if (item.getCode().equals(code)) {
                    return item.getCnName();
                }
            }
            return code;
        }

        public String toString() {
            return this.name();
        }

        public static List<PageElementModel> getList() {
            List<PageElementModel> list = new ArrayList<PageElementModel>();
            PageElementModel sm = null;
            for (PRODUCT_TYPE item : PRODUCT_TYPE.values()) {
                sm = new PageElementModel();
                sm.setElementCode(item.getCode());
                sm.setElementValue(item.getCnName());
                list.add(sm);
            }
            return list;
        }
    }

    /**
     * 团购类型
     */
    public static enum GROUPBUY_BRANCH_TYPE {

        PROD("产品"),
        BRANCH("商品");

        private String cnName;

        GROUPBUY_BRANCH_TYPE(String name) {
            this.cnName = name;
        }

        public String getCode() {
            return this.name();
        }

        public String getCnName() {
            return this.cnName;
        }

        public static String getCnName(String code) {
            for (PRODUCT_TYPE item : PRODUCT_TYPE.values()) {
                if (item.getCode().equals(code)) {
                    return item.getCnName();
                }
            }
            return code;
        }

        public String toString() {
            return this.name();
        }
    }

    /**
     * 获取属性
     *
     * @return
     */
    public String getValue(String key) {
        return properties.getProperty(key);
    }

    /**
     * 强制升级开关判断
     *
     * @return
     */
    public static boolean getForceUpgradeEnabled() {
        String enabled = Constant.getInstance().getValue("forceupgrade.enabled");
        if (StringUtils.isNotBlank(enabled)) {
            try {
                boolean falg = Boolean.valueOf(enabled);
                if (falg) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 点评有奖开关判断
     *
     * @return
     */
    public static boolean getCmtActivityEnabled() {
        String enabled = Constant.getInstance().getValue("cmtActivity.enabled");
        if (StringUtils.isNotBlank(enabled)) {
            try {
                boolean falg = Boolean.valueOf(enabled);
                if (falg) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static enum CLIENT_MOBILE_REGIST {
        OK("1"),//提示errorMessage
        ERROR("-1"),//提示errorMessage
        IMAGE_CODE_ERROR("-101"),//图片验证码失败
        NEED_VLIDATE_CODE("-5");//验证参数失败
        private String cnName;

        CLIENT_MOBILE_REGIST(String name) {
            this.cnName = name;
        }

        public String getCode() {
            return this.name();
        }

        public String getCnName() {
            return this.cnName;
        }

        public static String getCodeName(String code) {
            for (CLIENT_MOBILE_REGIST item : CLIENT_MOBILE_REGIST.values()) {
                if (item.getCode().equals(code)) {
                    return item.getCnName();
                }
            }
            return code;
        }

        public String toString() {
            return this.name();
        }
    }

    /**
     * 游玩人/联系人-证件类型
     *
     * @author wxliyong
     * @date 2015-7-28 11:05:05
     */
    public static enum CERTIFICATE_TYPE {
        ID_CARD("身份证"),
        HUZHAO("护照"),
        GANGAO("港澳通行证"),
        TAIBAO("台湾通行证"),// 主站、IOS
        TAIWAN("台湾通行证"),// 台湾通行证（安卓）7.3版本之前容错
        HUIXIANG("回乡证"),
        TAIBAOZHENG("台胞证"),
        //CHUSHENGZHENGMING("出生证明"),
        //HUKOUBO("户口簿"),
        //SHIBING("士兵证"),
        //JUNGUAN("军官证"),
        CUSTOMER_SERVICE_ADVICE("客服联系我提供");
        private String cnName;

        CERTIFICATE_TYPE(String cnName) {
            this.cnName = cnName;
        }

        public String getCode() {
            return this.name();
        }

        public String getCnName() {
            return this.cnName;
        }

        public static String getCodeName(String code) {
            for (CERTIFICATE_TYPE item : CERTIFICATE_TYPE.values()) {
                if (item.getCode().equals(code)) {
                    return item.getCnName();
                }
            }
            return code;
        }

        public String toString() {
            return this.name();
        }
    }

    /**
     * 性别
     */
    public static enum GENDER_TYPE {
        F("女"),
        M("男");
        private String cnName;

        GENDER_TYPE(String cnName) {
            this.cnName = cnName;
        }

        public String getCode() {
            return this.name();
        }

        public String getCnName() {
            return this.cnName;
        }

        public static String getCodeName(String code) {
            for (CERTIFICATE_TYPE item : CERTIFICATE_TYPE.values()) {
                if (item.getCode().equals(code)) {
                    return item.getCnName();
                }
            }
            return code;
        }

        public String toString() {
            return this.name();
        }
    }

    /**
     * 自由行价格日历的展示方式
     */
    public static enum TIME_PRICE_DISPLAY_TYPE {
        /**
         * 空白显示
         */
        DISPLAY_BLANK,
        /**
         * 正常显示
         */
        DISPLAY_NORMAL,
        /**
         * 显示 不可定
         */
        DISPLAY_NO_ORDER,
        /**
         * 显示售罄
         */
        DISPLAY_SOLD_OUT;

    }

    /**
     * 区域枚举类型
     */
    public static enum DISTRICT_TYPE {
        CONTINENT("洲", 5),
        COUNTRY("国家", 4),
        PROVINCE("省", 3),
        PROVINCE_DCG("直辖市", 3),
        PROVINCE_SA("特别行政区", 3),
        PROVINCE_AN("自治区", 3),
        CITY("市", 2),
        COUNTY("区/县", 1),
        TOWN("乡镇/街道", 0);

        private String cnName;
        /**
         * 标志当前行政区划等级，序列越大等级越高
         */
        private int index;

        public static String getCnName(String code) {
            for (DISTRICT_TYPE item : DISTRICT_TYPE.values()) {
                if (item.getCode().equals(code)) {
                    return item.getCnName();
                }
            }
            return code;
        }

        /**
         * 根据code获取行政区划的枚举
         */
        public static DISTRICT_TYPE getEnumByCode(String code) {
            for (DISTRICT_TYPE item : DISTRICT_TYPE.values()) {
                if (item.getCode().equals(code)) {
                    return item;
                }
            }
            return null;
        }

        DISTRICT_TYPE(String name, int index) {
            this.cnName = name;
            this.index = index;
        }

        public String getCode() {
            return this.name();
        }

        public String getCnName() {
            return this.cnName;
        }

        public int getIndex() {
            return this.index;
        }

        @Override
        public String toString() {
            return this.name();
        }
    }

    /**
     * 周
     */
    public static enum WEEK {
        SUNDAY("周日", 1),
        MONDAY("周一", 2),
        TUESDAY("周二", 3),
        WEDNESDAY("周三", 4),
        THURSDAY("周四", 5),
        FRIDAY("周五", 6),
        SATURDAY("周六", 7);

        private String cnName;
        private int index;

        WEEK(String name, int index) {
            this.cnName = name;
            this.index = index;
        }

        public String getCode() {
            return this.name();
        }

        public String getCnName() {
            return this.cnName;
        }

        public int getIndex() {
            return this.index;
        }

        public static String getCnName(String code) {
            for (WEEK item : WEEK.values()) {

                if (item.getCode().equals(code)) {
                    return item.getCnName();
                }
            }
            return code;
        }

        public static String getCnName(int index) {
            for (WEEK item : WEEK.values()) {
                if (item.getIndex() == index) {
                    return item.getCnName();
                }
            }
            return null;
        }

        public String toString() {
            return this.name();
        }
    }
}
