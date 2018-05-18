package com.tinnkm.application.util.wechat;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tinnkm.application.model.AccessToken;
import com.tinnkm.application.service.impl.SignatureServiceImpl;
import com.tinnkm.application.util.encode.EncodeUtils;
import com.tinnkm.application.util.httpClient.HttpClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

@Component
@EnableConfigurationProperties(WeChatProperties.class)
public class WeChatUtils {
    private static Logger log = LoggerFactory.getLogger(SignatureServiceImpl.class);
    @Autowired
    private WeChatProperties weChatProperties;
    @Autowired
    private HttpClientUtils httpClientUtils;
    private AccessToken accessToken;

    /**
     * 验证请求是来自于微信端
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     * @throws NoSuchAlgorithmException
     */
    public  boolean checkSignature(String signature,String timestamp,String nonce)  {
        log.info("start checkSignature,signature:{},timestamp:{},nonce:{}",signature,timestamp,nonce);
        String[] strings = {weChatProperties.getToken(), timestamp, nonce};
        Arrays.sort(strings);
        StringBuffer stringBuffer = new StringBuffer();
        Arrays.stream(strings).forEach(string -> {
            stringBuffer.append(string);
        });
        String digests = EncodeUtils.SHA1(stringBuffer.toString());
        log.info("this digest is {},this source is {}",digests,signature);
        return signature.equalsIgnoreCase(digests) ;
    }

    @Scheduled(fixedRate = 7200*1000)
    private void getAccessToken() throws IOException, URISyntaxException {
        log.info("Refresh accessToken,time:{}",new Date());
        HashMap<String, String> params = new HashMap<>();
        params.put("grant_type","client_credential");
        params.put("appid",weChatProperties.getAppId());
        params.put("secret",weChatProperties.getAppSecret());
        String resp = httpClientUtils.doGet(weChatProperties.getApiUrl() + "/token", params);
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        this.accessToken =  gson.fromJson(resp, AccessToken.class);
    }
    private String accessToken(){
        return this.accessToken.getAccessToken();
    }

}
