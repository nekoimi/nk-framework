package com.sakuraio.nk.web.wrapper;

import com.sakuraio.nk.web.utils.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

/**
 * nekoimi  2021/7/20 下午2:23
 */
@Slf4j
public class HttpRequestWrapper extends HttpServletRequestWrapper {
    /**
     * <p>保存请求体原始数据 以供后续读取请求体</p>
     */
    private final byte[] originalBody;

    public HttpRequestWrapper(HttpServletRequest request) {
        super(request);
        byte[] bytes = new byte[0];
        try {
            bytes = StreamUtils.copyToByteArray(request.getInputStream());
        } catch (IOException e) {
            log.error("Read request body error! " + e.getMessage());
        }
        originalBody = bytes;
    }


    @Override
    public ServletInputStream getInputStream() throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(originalBody);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
                // TODO document why this method is empty
            }

            @Override
            public int read() throws IOException {
                return inputStream.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return ArrayUtils.isEmpty(originalBody) ? null : new BufferedReader(new InputStreamReader(getInputStream()));
    }

    private static Boolean isNotIpAddress(String ipAddress) {
        return (StringUtils.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress));
    }

    @Override
    public String getRemoteAddr() {
        String ipAddress = getHeader("X-Forwarded-For");
        if (Boolean.TRUE.equals(isNotIpAddress(ipAddress))) {
            ipAddress = getHeader("Proxy-Client-IP");
        }
        if (Boolean.TRUE.equals(isNotIpAddress(ipAddress))) {
            ipAddress = getHeader("WL-Proxy-Client-IP");
        }
        if (Boolean.TRUE.equals(isNotIpAddress(ipAddress))) {
            ipAddress = super.getRemoteAddr();
            if ("127.0.0.1".equals(ipAddress)) {
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                    ipAddress = inet.getHostAddress();
                } catch (UnknownHostException ex) {
                    log.error(ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }
        if (Boolean.FALSE.equals(isNotIpAddress(ipAddress)) &&
                ipAddress.length() > 15 && ipAddress.contains(",")) {
            ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
        }
        return ipAddress;
    }

    public Map<String, String> queryAsMap() {
        return RequestUtils.queryAsMap(this.getQueryString());
    }

}