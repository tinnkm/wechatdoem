package com.tinnkm.application.util.httpclient;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author tinnkm
 */
@Component
public class HttpClientUtils {
    private final Logger log = LoggerFactory.getLogger(HttpClientUtils.class);
    private final CloseableHttpClient httpClient;
    private final String ENCODE = "UTF-8";

    @Autowired
    public HttpClientUtils(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * 无参get请求
     *
     * @param url 请求链接
     * @return 结果字符串
     * @throws ClientProtocolException 链接协议异常
     * @throws IOException             io异常
     * @autho tinnkm
     */
    public String doGet(String url) throws ClientProtocolException, IOException {
        log.info("start do get request,url:{}", url);
        // 创建http GET请求
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = this.httpClient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                return content;
            }
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return null;
    }

    /**
     * 有参get请求
     *
     * @param url 请求路径
     * @return 结果字符串
     * @throws URISyntaxException      uri地址异常
     * @throws IOException             io异常
     * @throws ClientProtocolException 链接异常
     */
    public String doGet(String url, Map<String, String> params) throws URISyntaxException, ClientProtocolException, IOException {
        URIBuilder uriBuilder = new URIBuilder(url);
        if (params != null) {
            for (String key : params.keySet()) {
                uriBuilder.setParameter(key, params.get(key));
            }
        }
        return this.doGet(uriBuilder.build().toString());
    }

    /**
     * 有参post请求
     *
     * @param url    请求路径
     * @param params 参数
     * @return 返回结果
     * @throws ClientProtocolException 链接异常
     * @throws IOException             io异常
     * @author tinnkm
     */
    public String doPost(String url, Map<String, String> params) throws ClientProtocolException, IOException {
        // 创建http POST请求
        HttpPost httpPost = new HttpPost(url);
        if (params != null) {
            // 设置2个post参数，一个是scope、一个是q
            List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
            for (String key : params.keySet()) {
                parameters.add(new BasicNameValuePair(key, params.get(key)));
            }
            // 构造一个form表单式的实体
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters);
            // 将请求实体设置到httpPost对象中
            httpPost.setEntity(formEntity);
        }
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = this.httpClient.execute(httpPost);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(response.getEntity(), ENCODE);

            }
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return null;
    }

    /**
     * 有参post请求,json交互
     *
     * @param url 请求链接
     * @param json json串
     * @return 解析结果
     * @throws ClientProtocolException
     * @throws IOException
     * @author trrinnkm
     * @date 2017年5月8日 下午3:33:01
     */
    public String doPostJson(String url, String json) throws ClientProtocolException, IOException {
        // 创建http POST请求
        HttpPost httpPost = new HttpPost(url);
        if (!StringUtils.isEmpty(json)) {
            //标识出传递的参数是 application/json
            StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(stringEntity);
        }
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = this.httpClient.execute(httpPost);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(response.getEntity(), "UTF-8");

            }
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return null;
    }

    /**
     * 无参post请求
     *
     * @param url
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     * @author tinnkm
     * @date 2017年5月8日 下午3:33:27
     */
    public String doPost(String url) throws ClientProtocolException, IOException {
        return this.doPost(url, null);
    }

}
