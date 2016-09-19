package com.prowo.common.util;

import com.alibaba.fastjson.serializer.*;
import com.prowo.common.annotation.SpVersion;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class FastJsonUtils {

    private static final SerializerFeature[] features = {SerializerFeature.WriteMapNullValue,// 输出空置字段
            SerializerFeature.WriteNullListAsEmpty,// list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullNumberAsZero,// 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullBooleanAsFalse,// Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullStringAsEmpty,
            //SerializerFeature.WriteJavaBeanNullFieldBeatyValue,// 字符类型字段如果为null，输出为""，而不是null
            SerializerFeature.DisableCircularReferenceDetect
    };

    public static Object toJson(Object object) {
        return FastJsonUtils.paser(object, null, false);
    }

    private static Object paser(Object object, final Long appVersion,
                                final boolean isIpad) {
        ValueFilter filter = new ValueFilter() {

            public Object process(Object object, String name, Object value) {
                Field field = ReflectionUtils
                        .findField(object.getClass(), name);

                if (field == null) {
                    String method = name.replaceFirst(name.substring(0, 1),
                            name.substring(0, 1).toUpperCase());
                    Method m = ReflectionUtils.findMethod(object.getClass(),
                            "get" + method);
                    if (object != null && m != null
                            && m.getGenericReturnType().equals(Date.class)) {
                        if (value == null) {
                            return "";
                        }
                    }
                }

                if (object != null && field != null
                        && field.getGenericType() != null
                        && field.getGenericType().toString().equals("T")) {
                    if (value == null) {
                        return new HashMap<String, Object>();
                    }
                }
                if (object != null && field != null
                        && field.getType().equals(Date.class)) {
                    if (value == null) {
                        return "";
                    }
                }
                if (object != null && field != null
                        && field.getType().equals(List.class)) {
                    if (value == null) {
                        return new ArrayList<Object>();
                    }
                }
                if (object != null && field != null
                        && field.getType().equals(Map.class)) {
                    if (value == null) {
                        return new HashMap<Object, Object>();
                    }
                }
                return value;

            }
        };
        List<SerializeFilter> filters = new ArrayList<SerializeFilter>();

        if (appVersion != null) {
            PropertyPreFilter properterFilter = new PropertyPreFilter() {

                public boolean apply(JSONSerializer serializer, Object object,
                                     String name) {
                    Field field = ReflectionUtils.findField(object.getClass(),
                            name);
                    if (field == null) {
                        String method = name.replaceFirst(name.substring(0, 1),
                                name.substring(0, 1).toUpperCase());
                        Method m = ReflectionUtils.findMethod(
                                object.getClass(), "get" + method);
                        if (m != null) {

                            /**
                             * 当客户端版本号 小于设定的版本号就过滤属性 appVersion 客户端版本号。
                             * spversion 支持的最低版本号
                             *
                             */
                            SpVersion spversion = m
                                    .getAnnotation(SpVersion.class);
                            if (isIpad) {
                                if (spversion != null
                                        && spversion.ipadGt() > appVersion
                                        .intValue()) {
                                    return false;
                                }
                            } else {
                                if (spversion != null
                                        && spversion.gt() > appVersion
                                        .intValue()) {
                                    return false;
                                }
                            }
                        }
                    } else {
                        SpVersion spversion = field
                                .getAnnotation(SpVersion.class);

                        if (isIpad) {
                            if (spversion != null
                                    && spversion.ipadGt() > appVersion
                                    .intValue()) {
                                return false;
                            }
                        } else {
                            if (spversion != null
                                    && spversion.gt() > appVersion.intValue()) {
                                return false;
                            }
                        }
                    }
                    return true;
                }
            };

            filters.add(properterFilter);
        }
        filters.add(filter);
        return toJson(object, filters, features);
    }

    public static boolean isNotNumber(Object object, Field field) {
        if (object != null
                && field != null
                && !(field.getType().equals(Long.class)
                || field.getType().equals(Integer.class)
                || field.getType().equals(int.class)
                || field.getType().equals(long.class)
                || field.getType().equals(float.class)
                || field.getType().equals(Float.class) || field
                .getType().equals(Number.class))) {
            return true;
        }
        return false;
    }

    public static Object toJsonFilterProperties(Object object, long appVersion,
                                                boolean isIpad) {
        return FastJsonUtils.paser(object, appVersion, isIpad);
    }

    private static Object toJson(Object object, List<SerializeFilter> filters,
                                 SerializerFeature... features) {
        SerializeWriter out = new SerializeWriter();

        try {
            JSONSerializer serializer = new JSONSerializer(out);
            for (com.alibaba.fastjson.serializer.SerializerFeature feature : features) {
                serializer.config(feature, true);
            }

            serializer.config(SerializerFeature.WriteDateUseDateFormat, true);
            for (SerializeFilter filter : filters) {
                if (filter != null) {
                    if (filter instanceof PropertyPreFilter) {
                        serializer.getPropertyPreFilters().add(
                                (PropertyPreFilter) filter);
                    }

                    if (filter instanceof NameFilter) {
                        serializer.getNameFilters().add((NameFilter) filter);
                    }

                    if (filter instanceof ValueFilter) {
                        serializer.getValueFilters().add((ValueFilter) filter);
                    }

                    if (filter instanceof PropertyFilter) {
                        serializer.getPropertyFilters().add(
                                (PropertyFilter) filter);
                    }

                    if (filter instanceof BeforeFilter) {
                        serializer.getBeforeFilters()
                                .add((BeforeFilter) filter);
                    }

                    if (filter instanceof AfterFilter) {
                        serializer.getAfterFilters().add((AfterFilter) filter);
                    }
                }
            }
            serializer.write(object);

            return out.toString();
        } finally {
            out.close();
        }
    }

    /**
     * 返回格式化JSON字符串。
     *
     * @param json 未格式化的JSON字符串。
     * @return 格式化的JSON字符串。
     */
    public static String formatJson(String json) {
        StringBuffer result = new StringBuffer();

        int length = json.length();
        int number = 0;
        char key = 0;

        // 遍历输入字符串。
        for (int i = 0; i < length; i++) {
            // 1、获取当前字符。
            key = json.charAt(i);

            // 2、如果当前字符是前方括号、前花括号做如下处理：
            if ((key == '[') || (key == '{')) {
                // （1）如果前面还有字符，并且字符为“：”，打印：换行和缩进字符字符串。
                if ((i - 1 > 0) && (json.charAt(i - 1) == ':')) {
                    result.append('\n');
                    result.append(indent(number));
                }

                // （2）打印：当前字符。
                result.append(key);

                // （3）前方括号、前花括号，的后面必须换行。打印：换行。
                result.append('\n');

                // （4）每出现一次前方括号、前花括号；缩进次数增加一次。打印：新行缩进。
                number++;
                result.append(indent(number));

                // （5）进行下一次循环。
                continue;
            }

            // 3、如果当前字符是后方括号、后花括号做如下处理：
            if ((key == ']') || (key == '}')) {
                // （1）后方括号、后花括号，的前面必须换行。打印：换行。
                result.append('\n');

                // （2）每出现一次后方括号、后花括号；缩进次数减少一次。打印：缩进。
                number--;
                result.append(indent(number));

                // （3）打印：当前字符。
                result.append(key);

                // （4）如果当前字符后面还有字符，并且字符不为“，”，打印：换行。
                if (((i + 1) < length) && (json.charAt(i + 1) != ',')) {
                    result.append('\n');
                }

                // （5）继续下一次循环。
                continue;
            }

            // 4、如果当前字符是逗号。逗号后面换行，并缩进，不改变缩进次数。
            if ((key == ',')) {
                result.append(key);
                result.append('\n');
                result.append(indent(number));
                continue;
            }

            // 5、打印：当前字符。
            result.append(key);
        }

        return result.toString();
    }

    /**
     * 返回指定次数的缩进字符串。每一次缩进三个空格。
     *
     * @param number 缩进次数。
     * @return 指定缩进次数的字符串。
     */
    private static String indent(int number) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < number; i++) {
            result.append("   ");
        }
        return result.toString();
    }

    public static void main(String[] args) {
//		RopResponseContent rc = new RopResponseContent();
//		List<RopPassInfoResponse> list = new ArrayList<RopPassInfoResponse>();
//		RopPassInfoResponse response = new RopPassInfoResponse();
//		// response.setInValidTime(new Date());
//
//		// list.add(response);
//		rc.setList(list);

        String result = FastJsonUtils.formatJson("");
        // Object o = FastJsonUtils.toJsonFilterProperties(rc,530);
        // System.out.println(o);
        // RopResponseContent rc = new RopResponseContent();
        // List<RopPassInfoResponse> list = new
        // ArrayList<RopPassInfoResponse>();
        // RopPassInfoResponse response =new RopPassInfoResponse();
        // response.setInValidTime(new Date());
        //
        // list.add(response);
        // rc.setList(list);
        //
        // Object o = FastJsonUtils.toJsonFilterProperties(rc,530);
        // System.out.println(o);
    }
}
