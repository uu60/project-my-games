package com.octopus.mygamesbackend.utils.websocket;

import java.util.HashMap;

public class Resp extends HashMap<String, Object> {

    private Resp(int opt) {
        this.put("opt", opt);
    }

    public static Resp ok(int opt) {
        return new Resp(opt);
    }

    public Resp put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
