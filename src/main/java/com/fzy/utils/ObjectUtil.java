package com.fzy.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Object进行解析并且转换成Map键值对的形式
 *
 * @author yushu.zhao
 * @create 2021-05-21 10:34
 */
public class ObjectUtil {

    private static final Logger logger = LoggerFactory.getLogger(ObjectUtil.class);


    /**
     *
     * 取利用反射获取类里面的值和名称
     *
     * @param obj 参数
     */
    public static Map<String,Object> objectToMap(Object obj) throws IllegalAccessException {
        Map<String,Object> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        System.out.println(clazz);
        for (Field field : clazz.getDeclaredFields()){
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            map.put(fieldName, value);
        }
        return map;
    }








}
