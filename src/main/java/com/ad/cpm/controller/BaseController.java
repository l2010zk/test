package com.ad.cpm.controller;

import com.ad.cpm.utils.Tools;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by lzk on 2017/3/8.
 */
public class BaseController {

    public static final String COOKIE_NAME_NVID = "_yl_nvid";
    public static final String COOKIE_NAME_CRAETETIME = "_yl_ft";
    public static final String COOKIE_NAME_PAGE_ID = "_yl_pageid";

    protected String getCookieValue(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (key.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
    /**
     * 删除Cookie
     *
     * @param key
     * @param request
     * @param response
     */
    protected void removeCookieValue(String key,String domain,HttpServletRequest request,
                                     HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            if (!Tools.isEmpty(key)) {
                for (Cookie cookie : cookies) {
                    if (key.equals(cookie.getName())) {
                        cookie.setDomain(domain);
                        cookie.setPath("/");
                        cookie.setMaxAge(-10);
                        response.addCookie(cookie);
                    }
                }
            } else {
                for (Cookie cookie : cookies) {
                    cookie.setDomain(domain);
                    cookie.setPath("/");
                    cookie.setMaxAge(-10);
                    response.addCookie(cookie);
                }
            }
        }
    }

    /**
     * lizk
     * @param request
     * @param key
     * @return
     */
    protected String getCookieValueWithspecial(HttpServletRequest request,
                                               String key) {
        try {
            String allCookieStr = request.getHeader("Cookie");
            if (allCookieStr != null) {
                key = key + "=";
                int begin = allCookieStr.indexOf(key);
                if (begin >= 0) {
                    int end = allCookieStr.indexOf(";", begin);
                    if (end >= 0) {
                        return allCookieStr
                                .substring(begin + key.length(), end);
                    } else {
                        return allCookieStr.substring(begin + key.length());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 写cookie
     *
     * @param response
     * @param key
     * @param value
     */
    protected void setCookieValue(HttpServletResponse response, String domain,
                                  String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setDomain(domain);
        cookie.setPath("/");
        cookie.setMaxAge(99999999);
        response.addCookie(cookie);
    }

    /**
     *
     * @Title: getIpAddr
     * @Description: (获取用户IP)
     * @param HttpServletRequest
     *            request
     * @return String IP
     */
    protected String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");

        if (ip != null && !"unknown".equalsIgnoreCase(ip)
                && ip.indexOf(",") > 0) {
            ip = ip.split(",")[0];
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取客户浏览器信息和系统信息
     *
     * @param request
     * @return
     */
    protected String getClientInfo(HttpServletRequest request) {
        String Agent = request.getHeader("User-Agent");
        if (Agent == null || Agent.length() == 0) {
            return "unkown";
        }
        return Agent;
    }

    /**
     * 参数解码
     *
     * @param param
     * @return String decodePrameter
     */
    protected String decodePrameter(String param) {
        try {
            if (param != null) {
                String str = new String(param.getBytes("ISO-8859-1"), "UTF-8");
                return URLDecoder.decode(str, "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
