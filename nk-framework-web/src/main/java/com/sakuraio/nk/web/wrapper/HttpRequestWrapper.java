package com.sakuraio.nk.web.wrapper;

import cn.hutool.core.lang.Dict;
import com.sakuraio.nk.util.http.RequestUtils;
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

/**
 * nekoimi  2021/7/20 下午2:23
 *
 * <p>http请求使用流读取数据，读取一次之后就不能重复读取了，这里实现自定义的wrapper，包装 {@link HttpServletRequest HttpServletRequest} 对象，
 * 缓存请求体到本地，供后续重复读取</p>
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
                // ignore
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

    public Dict getQueryDict() {
        return RequestUtils.getQueryDict(this.getQueryString());
    }

}
