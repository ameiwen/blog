package com.blog.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static Logger logger = LogManager.getLogger(JsonUtil.class);

    public JsonUtil() {
    }

    public static Map<String, Object> jsonToMap(String json) {
        Object map = new HashMap();

        try {
            map = (Map)mapper.readValue(json, Map.class);
        } catch (Exception var3) {
            logger.error(var3);
        }

        return (Map)map;
    }

    public static <T> T jsonToObject(String json, Class<T> cls) {
        Object t = null;

        try {
            t = mapper.readValue(json, cls);
        } catch (Exception var4) {
            logger.error(var4);
        }

        return (T) t;
    }

    public static String objectToJson(Object o) {
        String s = null;

        try {
            s = mapper.writeValueAsString(o);
        } catch (Exception var3) {
            logger.error(var3);
        }

        return s;
    }

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}

