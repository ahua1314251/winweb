package org.winker.winweb.utils.database;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author ：tom
 * @date ：Created in 2021/10/12 10:29 上午
 * @description：
 * @modified By：
 * @version: $
 */
public class JacksonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public  static <T> T toObject(String str , JavaType javaType){
        try {
           return objectMapper.readValue(str,javaType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public  static <T> T toObject(String str , Class<T> valueType){
        try {
            return objectMapper.readValue(str,valueType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
