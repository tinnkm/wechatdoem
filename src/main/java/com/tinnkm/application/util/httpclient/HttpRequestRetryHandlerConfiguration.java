package com.tinnkm.application.util.httpclient;

import org.apache.http.*;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

/**
 * @author tinnkm
 */
@Configuration
@EnableConfigurationProperties(HttpClientProperties.class)
public class HttpRequestRetryHandlerConfiguration {
    @Autowired
    public HttpClientProperties httpClientProperties;

    /**
     * 连接重试
     *
     * @return
     */
    @Bean
    public HttpRequestRetryHandler httpRequestRetryHandler() {
        final int retryTime = httpClientProperties.getRetryTime();
        return (e, i, httpContext) -> {

            if (i >= retryTime) {
                return false;
            }
            // 服务器断掉客户端的链接异常
            if (e instanceof NoHttpResponseException) {
                return true;
            }
            // time out 超时重试
            if (e instanceof InterruptedIOException) {
                return true;
            }
            // Unknown host
            if (e instanceof UnknownHostException) {
                return false;
            }
            // Connection refused
            if (e instanceof ConnectTimeoutException) {
                return false;
            }
            // SSL handshake exception
            if (e instanceof SSLException) {
                return false;
            }
            HttpClientContext clientContext = HttpClientContext.adapt(httpContext);
            HttpRequest request = clientContext.getRequest();
            return !(request instanceof HttpEntityEnclosingRequest);
        };
    }

    /**
     * 连接池管理
     *
     * @return
     */
    @Bean
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(httpClientProperties.getTimeToLive(), TimeUnit.SECONDS);
        poolingHttpClientConnectionManager.setMaxTotal(httpClientProperties.getConnMaxTotal());
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(httpClientProperties.getMaxPerRoute());
        return poolingHttpClientConnectionManager;
    }

    /**
     * 保持连接的策略
     *
     * @return
     */
    @Bean
    public ConnectionKeepAliveStrategy connectionKeepAliveStrategy() {
        return (httpResponse, httpContext) -> {
            BasicHeaderElementIterator basicHeaderElementIterator = new BasicHeaderElementIterator(httpResponse.headerIterator(HTTP.CONN_KEEP_ALIVE));
            while (basicHeaderElementIterator.hasNext()) {
                HeaderElement headerElement = basicHeaderElementIterator.nextElement();
                String name = headerElement.getName();
                String value = headerElement.getValue();
                if (value != null && "timeout".equalsIgnoreCase(name)) {
                    return Long.parseLong(value) * 1000;
                }
            }
            return httpClientProperties.getKeepAliveTime() * 1000;
        };
    }

    @Bean
    @ConditionalOnProperty(prefix = "spring.httpclient",name = "proxy-enable",havingValue = "true")
    public DefaultProxyRoutePlanner defaultProxyRoutePlanner(){
        HttpHost httpHost = new HttpHost(httpClientProperties.getProxyHost(), httpClientProperties.getProxyPort());
        return new DefaultProxyRoutePlanner(httpHost);

    }

    @Bean
    public RequestConfig requestConfig(){
        return RequestConfig.custom().setConnectionRequestTimeout(httpClientProperties.getConnectRequestTimeout())
                .setConnectTimeout(httpClientProperties.getConnectTimeout())
                .setSocketTimeout(httpClientProperties.getSocketTimeout())
                .build();
    }

}
