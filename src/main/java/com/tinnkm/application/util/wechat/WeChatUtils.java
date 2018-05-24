package com.tinnkm.application.util.wechat;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tinnkm.application.model.User;
import com.tinnkm.application.util.wechat.model.AccessToken;
import com.tinnkm.application.util.encode.EncodeUtils;
import com.tinnkm.application.util.httpClient.HttpClientUtils;
import com.tinnkm.application.util.json.JsonUtils;
import com.tinnkm.application.util.wechat.model.Menu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
     * 获取用户openId
     *

     * @retu     */
    public Map getAuth(String code) throws IOException, URISyntaxException {
        HashMap<String, String> params = new HashMap<>();
        params.put("appid",weChatProperties.getAppId());
        params.put("secret",weChatProperties.getAppSecret());
        params.put("code",code);
        params.put("grant_type","authorization_code");
        String resps = httpClientUtils.doGet("https://api.weixin.qq.com/sns/oauth2/access_token", params);
        params.clear();
        params.put("openid",JsonUtils.getFieldValue(resps,"openid"));
        // 这个access_token与我们系统中的不是一个，只做获取用户信息使用
        params.put("accessToken",JsonUtils.getFieldValue(resps,"access_token"));
        return params;
    }

    /**
     * 获取用户详细信息(此接口暂时用不到不处理)
     * @return
     */
    public Map getUserInfo(){
        return null;
    }

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

    //region token

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
    //endregion

    public boolean createMenu(Menu menu) throws IOException {
        String json = JsonUtils.object2json(menu);
        log.info("create menu，params：" + json);
        log.info("token is {}", accessToken());
        String resp = httpClientUtils.doPostJson(weChatProperties.getApiUrl() + "/menu/create?access_token=" + accessToken(), json);
        String errcode = JsonUtils.getFieldValue(resp, "errcode");
        if ("0".equalsIgnoreCase(errcode)) {
            return true;
        }
        return false;
    }

}
