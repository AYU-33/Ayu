package com.nowcoder.community.util;/*
    @author AYU
    */

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.UUID;

public class CommunityUtil {
    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    //MD5加密
    public static String md5(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }
    //处理json
    public static String getJsonString(int code, String msg, Map<String, Object> map){
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        if (map != null){
            for(String key : map.keySet()) {
                json.put(key, map.get(key));
            }
        }
        return json.toJSONString();
    }
    public static String getJsonString(int code, String msg){
        return getJsonString(code, msg, null);
    }
    public static String getJsonString(int code){
        return getJsonString(code, null, null);
    }
}
