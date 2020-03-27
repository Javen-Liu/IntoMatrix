package com.matrix.utils;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Javen-Liu
 * @version 1.0
 * @date 2020/3/27 20:21
 * @github https://github.com/Javen-Liu
 */
public class DateJsonValueProcessor implements JsonValueProcessor {
    private String format;

    public DateJsonValueProcessor(String format) {
        this.format = format;
    }

    @Override
    public Object processArrayValue(Object o, JsonConfig jsonConfig) {
        return null;
    }

    @Override
    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
        if (value == null) {
            return "";
        }
        if (value instanceof Timestamp) {
            return new SimpleDateFormat(this.format).format(value);
        }
        if (value instanceof Date) {
            return new SimpleDateFormat(this.format).format(value);
        }
        return value.toString();
    }
}
