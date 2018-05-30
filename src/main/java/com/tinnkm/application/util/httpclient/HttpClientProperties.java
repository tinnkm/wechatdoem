package com.tinnkm.application.util.httpclient;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tinnkm
 */
@ConfigurationProperties(prefix = "spring.httpclient")
public class HttpClientProperties {
    /**
     *  重试次数
     */
    private int retryTime = 3;
    /**
     * 最大连接数
     */
    private int connMaxTotal = 20;
    private int maxPerRoute = 20;
    /**
     * 连接存活时间（s）
     */
    private int timeToLive = 60;
    /**
     * 保持连接时间（s）
     */
    private int keepAliveTime = 30;
    /**
     * 代理
     */
    private boolean proxyEnable = false;
    private String proxyHost;
    private int proxyPort = 8080;
    /**
     * 连接超时时间
     */
    private int connectTimeout = 2000;
    /**
     * 从连接池中获取连接超时时间
     */
    private int connectRequestTimeout = 2000;
    /**
     * 请求超时时间
     */
    private int socketTimeout = 2000;

    //region getter/setter

    public int getConnMaxTotal() {
        return connMaxTotal;
    }

    public void setConnMaxTotal(int connMaxTotal) {
        this.connMaxTotal = connMaxTotal;
    }

    public int getMaxPerRoute() {
        return maxPerRoute;
    }

    public void setMaxPerRoute(int maxPerRoute) {
        this.maxPerRoute = maxPerRoute;
    }

    public int getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(int timeToLive) {
        this.timeToLive = timeToLive;
    }

    public int getRetryTime() {
        return retryTime;
    }

    public void setRetryTime(int retryTime) {
        this.retryTime = retryTime;
    }

    public int getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(int keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    public boolean isProxyEnable() {
        return proxyEnable;
    }

    public void setProxyEnable(boolean proxyEnable) {
        this.proxyEnable = proxyEnable;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getConnectRequestTimeout() {
        return connectRequestTimeout;
    }

    public void setConnectRequestTimeout(int connectRequestTimeout) {
        this.connectRequestTimeout = connectRequestTimeout;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }
    //endregion
}
