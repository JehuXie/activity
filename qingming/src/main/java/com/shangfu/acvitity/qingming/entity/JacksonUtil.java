package com.shangfu.acvitity.qingming.entity;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author JehuXie
 * @Description: ${todo}
 * @date 2018/3/29 0029下午 5:46
 */
public class JacksonUtil {
    private static final ObjectMapper objectMapper = getInstance();
    private static final String XML_ENCODING = "<?xml version=\"1.0\" encoding=\"GB2312\"?>";

    private JacksonUtil() {
    }

    public static ObjectMapper getInstance() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        objectMapper.setDateFormat(sdf);
        return objectMapper;
    }

    public static String obj2json(Object obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }

    public static <T> T json2pojo(String jsonStr, Class<T> clazz) throws Exception {
        return objectMapper.readValue(jsonStr, clazz);
    }

    public static <T> Map<String, Object> json2map(String jsonStr) throws Exception {
        return (Map)objectMapper.readValue(jsonStr, Map.class);
    }

    public static <T> Map<String, T> json2map(String jsonStr, Class<T> clazz) throws Exception {
        Map<String, Map<String, Object>> map = (Map)objectMapper.readValue(jsonStr, new TypeReference<Map<String, T>>() {
        });
        Map<String, T> result = new HashMap();
        Iterator var4 = map.entrySet().iterator();

        while(var4.hasNext()) {
            Map.Entry<String, Map<String, Object>> entry = (Map.Entry)var4.next();
            result.put(entry.getKey(), map2pojo((Map)entry.getValue(), clazz));
        }

        return result;
    }

    public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz) throws Exception {
        List<Map<String, Object>> list = (List)objectMapper.readValue(jsonArrayStr, new TypeReference<List<T>>() {
        });
        List<T> result = new ArrayList();
        Iterator var4 = list.iterator();

        while(var4.hasNext()) {
            Map<String, Object> map = (Map)var4.next();
            result.add(map2pojo(map, clazz));
        }

        return result;
    }

    public static <T> T map2pojo(Map map, Class<T> clazz) {
        return objectMapper.convertValue(map, clazz);
    }


    public static <T> T map2bean(Class<T> beanClass, Map<String, Object> map) throws Exception {
        if (map == null) {
            return beanClass.newInstance();
        } else {
            T bean = beanClass.newInstance();
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass(), Object.class);
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
            if (pds != null && pds.length > 0) {
                PropertyDescriptor[] var5 = pds;
                int var6 = pds.length;

                for(int var7 = 0; var7 < var6; ++var7) {
                    PropertyDescriptor pd = var5[var7];
                    String key = StringUtils.capitalize(pd.getName());
                    Object value = map.get(key);
                    if (value instanceof Map) {
                        Object obj = map2bean(pd.getPropertyType(), (Map)value);
                        pd.getWriteMethod().invoke(bean, obj);
                    } else {
                        pd.getWriteMethod().invoke(bean, value);
                    }
                }
            }

            return bean;
        }
    }


}

