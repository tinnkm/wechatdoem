package com.tinnkm.application.util.wechat;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tinnkm.application.model.AccessToken;
import com.tinnkm.application.model.MenuGroup;
import com.tinnkm.application.util.encode.EncodeUtils;
import com.tinnkm.application.util.httpClient.HttpClientUtils;
import com.tinnkm.application.util.json.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
@EnableConfigurationProperties(WeChatProperties.class)
public class WeChatUtils {
    private static Logger log = LoggerFactory.getLogger(WeChatUtils.class);
    @Autowired
    private WeChatProperties weChatProperties;
    @Autowired
    private HttpClientUtils httpClientUtils;
    private AccessToken accessToken;
    private ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue<AccessToken>();


    /**
     * 验证请求是来自于微信端
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     * @throws NoSuchAlgorithmException
     */
    public boolean checkSignature(String signature, String timestamp, String nonce) {
        log.info("start checkSignature,signature:{},timestamp:{},nonce:{}", signature, timestamp, nonce);
        String[] strings = {weChatProperties.getToken(), timestamp, nonce};
        Arrays.sort(strings);
        StringBuffer stringBuffer = new StringBuffer();
        Arrays.stream(strings).forEach(string -> {
            stringBuffer.append(string);
        });
        String digests = EncodeUtils.SHA1(stringBuffer.toString());
        log.info("this digest is {},this source is {}", digests, signature);
        return signature.equalsIgnoreCase(digests);
    }

    /**
     * 除setAccessToken外禁止手动调用此方法
     *
     * @throws IOException
     * @throws URISyntaxException
     */
    public String AutoRefreshAccessToken() throws IOException, URISyntaxException {
        log.info("get accessToken,time:{}", new Date());
        HashMap<String, String> params = new HashMap<>();
        params.put("grant_type", "client_credential");
        params.put("appid", weChatProperties.getAppId());
        params.put("secret", weChatProperties.getAppSecret());
        String resp = null;
        return httpClientUtils.doGet(weChatProperties.getApiUrl() + "/token", params);
    }

    /**
     * 禁止手动调用此方法
     * 每隔两小时刷新一次，因为accesstoken两小时过期
     * 这里采用7100秒是用来防止网络延迟
     * accesstoken在新旧交替的5分钟内，旧的accesstoken还是可用
     * scheduled会开启一个子线程，所以在此处借用队列来存储子线程中获取的token
     *
     * @throws IOException
     * @throws URISyntaxException
     */
    @Scheduled(fixedRate = 7100 * 1000)
    public void setAccessToken() throws IOException, URISyntaxException {
        log.info("Refresh accessToken,time:{},and save it to cache", new Date());
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        queue.add(gson.fromJson(AutoRefreshAccessToken(), AccessToken.class));
        log.info("-------------done------------");
    }

    public String accessToken() {
        while (this.accessToken == null) {
            AccessToken take = (AccessToken) queue.poll();
            this.accessToken = take;
        }
        return this.accessToken.getAccessToken();
    }

    public boolean createMenu(MenuGroup menuGroup) throws IOException{
        String json = JsonUtils.object2json(menuGroup);
        log.info("create menu，params：" + json);
        HashMap<String, String> params = new HashMap<>();
        log.info("token is {}", accessToken());
        params.put("access_token", accessToken());
        params.put("body", json);
        String resp = httpClientUtils.doPost(weChatProperties.getApiUrl() + "/menu/create", params);
        String errcode = JsonUtils.getFieldValue(resp, "errcode");
        if ("0".equalsIgnoreCase(errcode)){
            return true;
        }
        return false;
    }

}
