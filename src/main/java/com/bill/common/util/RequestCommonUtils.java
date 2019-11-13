package com.bill.common.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * request工具类
 *
 * @author f
 * @date 2019-11-13
 */
public class RequestCommonUtils {

    /**
     * 获取调用者ip
     *
     * @return
     */
    public static String getClientIp() {
        try {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = servletRequestAttributes.getRequest();
            String ip = request.getHeader("X-Forwarded-For");
            if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
                int index = ip.indexOf(",");
                return index != -1 ? ip.substring(0, index) : ip;
            } else {
                ip = request.getHeader("X-Real-IP");
                return StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip) ? ip : request.getRemoteAddr();
            }
        } catch (Exception var3) {
            return "";
        }
    }

    /**
     * 获取服务器ip
     *
     * @return
     */
    public static String getServerIp() {
        try {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = servletRequestAttributes.getRequest();
            return request.getLocalAddr();
        } catch (Exception var1) {
            return "";
        }
    }

    /**
     * 获取请求头
     *
     * @param request
     * @return
     */
    public static Map<String, String> getHeadersInfo(HttpServletRequest request) {
        Map<String, String> map = new HashMap();
        Enumeration headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

    /**
     * 取请求头参数
     *
     * @param headerName
     * @return
     */
    public static String getRequetHeader(String headerName) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            String value = request.getHeader(headerName);
            return value;
        }
        return null;
    }

    /**
     * 取请求url
     *
     * @return
     */
    public static String getUrl() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            String value = request.getRequestURI();
            return value;
        }
        return null;
    }

    /**
     * 取请求方法
     *
     * @return
     */
    public static String getMethod() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            String value = request.getMethod();
            return value;
        }
        return null;
    }

    /**
     * 取请求params
     *
     * @return
     */
    public static Map<String, String> getParams() {
        Map<String, String> map = new HashMap();
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            Enumeration parameterNames = request.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String key = (String) parameterNames.nextElement();
                String value = request.getParameter(key);
                map.put(key, value);
            }
        }
        return map;
    }
}
