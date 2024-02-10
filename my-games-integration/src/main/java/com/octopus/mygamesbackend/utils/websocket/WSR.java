package com.octopus.mygamesbackend.utils.websocket;

import java.util.HashMap;

public class WSR extends HashMap<String, Object> {

    private WSR(int opt) {
        this.put("opt", opt);
    }

    public static WSR ok(int opt) {
        return new WSR(opt);
    }

    public WSR put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
