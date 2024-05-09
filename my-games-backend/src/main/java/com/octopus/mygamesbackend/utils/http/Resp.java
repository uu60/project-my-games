package com.octopus.mygamesbackend.utils.http;

import com.octopus.mygamesbackend.utils.properties.MyConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * 用来封装http返回信息的类
 */
public class Resp extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    private Resp() {
        put("code", MyConstants.HTTP_SUCCESS);
    }

    public static Resp error() {
        Resp r = new Resp();
        r.put("code", MyConstants.HTTP_DEFAULT_ERROR);
        return r;
    }

    public static Resp error(int code, String msg) {
        Resp r = new Resp();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static Resp error(int code) {
        Resp r = new Resp();
        r.put("code", code);
        return r;
    }

    public static Resp ok(String msg) {
        Resp r = new Resp();
        r.put("msg", msg);
        return r;
    }

    public static Resp ok(Map<String, Object> map) {
        Resp r = new Resp();
        r.putAll(map);
        return r;
    }

    public static Resp ok() {
        return new Resp();
    }

    public Resp put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
