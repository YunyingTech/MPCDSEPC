package com.yytech.mpcdsepc.entity;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message<T> {
    private String type;
    private T data;

    public static <T> String OnlineCount (int num){
        return JSON.toJSONString(new Message("onlineCount", num));
    }
    public static <T> String Lock (String id){
        return JSON.toJSONString(new Message("Lock", id));
    }

    public static <T> String info (T data){
        return JSON.toJSONString(new Message("info", data));
    }
    public static <T> String system (T data){
        return JSON.toJSONString(new Message("system", data));
    }

}
