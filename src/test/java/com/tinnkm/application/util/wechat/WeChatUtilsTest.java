package com.tinnkm.application.util.wechat;

import com.tinnkm.application.ApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class WeChatUtilsTest extends ApplicationTests {

    @Autowired
    private WeChatUtils weChatUtils;
    @Test
    public void getAccessToken() throws IOException, URISyntaxException {
        //weChatUtils.getAccessToken();
    }
}