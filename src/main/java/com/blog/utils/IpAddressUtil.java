package com.blog.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class IpAddressUtil {
    private static Logger logger = LogManager.getLogger(IpAddressUtil.class);

    public IpAddressUtil() {
    }

    public static String getRemoteIp(HttpServletRequest request) {
        logger.info("--user agent--" + request.getHeader("User-Agent"));
        Enumeration<String> headNames = request.getHeaderNames();
        if (headNames != null) {
            logger.info("loop request head start");

            while(headNames.hasMoreElements()) {
                logger.info("-head-name-:" + (String)headNames.nextElement());
            }

            logger.info("loop request head end");
        }

        String ipAddress = request.getHeader("X-Real-IP");
        if (Misc.isStringEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("X-Forwarded-For");
        }

        if (Misc.isStringEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }

        if (Misc.isStringEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }

        if (Misc.isStringEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("REMOTE-HOST");
        }

        if (Misc.isStringEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if ("127.0.0.1".equals(ipAddress) || "0:0:0:0:0:0:0:1".equals(ipAddress)) {
                InetAddress inet = null;

                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException var5) {
                    var5.printStackTrace();
                }

                ipAddress = inet.getHostAddress();
            }
        }

        if (ipAddress != null && ipAddress.length() > 15 && ipAddress.indexOf(",") > 0) {
            ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
        }

        return ipAddress;
    }
}