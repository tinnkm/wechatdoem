package com.tinnkm.application.util.httpClient;

import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("HttpClient")
public class HttpClientManagerFactoryBean implements FactoryBean<CloseableHttpClient>,InitializingBean,DisposableBean {
    /**
     * FactoryBean生成的目标对象
     */
    private CloseableHttpClient client;
    @Autowired
    private ConnectionKeepAliveStrategy connectionKeepAliveStrategy;
    @Autowired
    private HttpRequestRetryHandler httpRequestRetryHandler;
    @Autowired(required = false)
    private DefaultProxyRoutePlanner proxyRoutePlanner;
    @Autowired
    private PoolingHttpClientConnectionManager poolingHttpClientConnectionManager;
    @Autowired
    private RequestConfig config;
    // 销毁上下文的时候销毁httpclient实例
    @Override
    public void destroy() throws Exception {
        /*
         * 调用httpClient.close()会先shut down connection manager，然后再释放该HttpClient所占用的所有资源，
         * 关闭所有在使用或者空闲的connection包括底层socket。由于这里把它所使用的connection manager关闭了，
         * 所以在下次还要进行http请求的时候，要重新new一个connection manager来build一个HttpClient,
         * 也就是在需要关闭和新建Client的情况下，connection manager不能是单例的.
         */
        if(null != this.client){
            this.client.close();
        }
    }

    @Override
    public CloseableHttpClient getObject() throws Exception {
        return this.client;
    }

    @Override
    public Class<?> getObjectType() {
        return (this.client == null ? CloseableHttpClient.class : this.client.getClass());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.client = HttpClients.custom().setConnectionManager(poolingHttpClientConnectionManager)
                .setRetryHandler(httpRequestRetryHandler)
                .setKeepAliveStrategy(connectionKeepAliveStrategy)
                .setRoutePlanner(proxyRoutePlanner)
                .setDefaultRequestConfig(config)
                .build();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
