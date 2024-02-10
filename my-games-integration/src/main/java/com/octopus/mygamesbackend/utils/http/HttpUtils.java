package com.octopus.mygamesbackend.utils.http;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;

public class HttpUtils {
    private static HashSet<String> URL_NO_NEED_LOGIN_SET =
            new HashSet<>(Arrays.asList("/login/*", "/register/*", "/kaptcha", "/", "/static/*"));

    private static boolean requestMappingFit(String reqMap, HashSet<String> set) {
        if (reqMap.equals("")) {
            reqMap = "/";
        }
        if (set.contains(reqMap)) {
            return true;
        }
        String[] parts = reqMap.split("/");
        StringBuilder cur = new StringBuilder();
        for (String part : parts) {
            if (part.equals("")) {
                continue;
            }
            cur.append("/");
            cur.append(part);
            if (set.contains(cur.toString() + "/*")) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNoNeedLogin(HttpServletRequest request) {
        return requestMappingFit(getRequestMappingUrl(request), URL_NO_NEED_LOGIN_SET);
    }

    private static String getRequestMappingUrl(HttpServletRequest request) {
        return request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
    }

    private static String getRemoteIp(HttpServletRequest request) {
        return request.getRemoteHost();
    }
}
