package com.prowo.common.utils;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

public class DateConverter implements Converter {

	public Object convert(Class type, Object value) {
		if (value == null) {
			return null;
		}
		if (value instanceof Date) {
			return value;
		}
		if (value instanceof Long) {
			Long longValue = (Long) value;
			return new Date(longValue.longValue());
		}
		if (value instanceof String) {
			String dateStr = (String) value;
			if (StringUtils.isEmpty(dateStr)) {
				return null;
			}
			Date date = null;
			try {
				date = DateUtils.parseDate(value.toString(), new String[] {
						"yyyy-MM-dd HH:mm:ss.SSS", "yyyy-MM-dd HH:mm:ss",
						"yyyy-MM-dd HH:mm", "yyyy-MM-dd" });
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return date;
		}
		return null;
	}
}
