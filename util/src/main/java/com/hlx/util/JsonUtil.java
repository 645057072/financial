package com.hlx.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;

public class JsonUtil {
    public static final Logger LOGGER= LoggerFactory.getLogger(JsonUtil.class);
    //创建对象影响器（mapper）
    private static final ObjectMapper MAPPER=new ObjectMapper();

    static{
        MAPPER.enable(SerializationFeature.WRITE_NULL_MAP_VALUES);
        MAPPER.enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
    }

    public static void setDateFormat(DateFormat dateFormat){
        MAPPER.setDateFormat(dateFormat);
    }
    /**
     *
     * @param date（序列化）
     * @return
     */
    public static String objectTojson(Object date){
        try {
            return MAPPER.writeValueAsString(date);
        }catch(JsonProcessingException e){
            LOGGER.error("to json exception.", e);
            throw new JSONException("把对象转换为JSON时出错了", e);
        }
    }
}

final class JSONException extends RuntimeException{
public JSONException(final String message) {
        super(message);
        }
public JSONException(final String message, final Throwable cause) {
        super(message, cause);
        }
}

