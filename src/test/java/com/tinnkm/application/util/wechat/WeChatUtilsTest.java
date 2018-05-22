package com.tinnkm.application.util.wechat;

import com.tinnkm.application.ApplicationTests;
import com.tinnkm.application.util.wechat.model.ViewButton;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class WeChatUtilsTest extends ApplicationTests {

    @Autowired
    private WeChatUtils weChatUtils;
    @Test
    public void getAccessToken() throws IOException, URISyntaxException {
        //weChatUtils.getAccessToken();
    }

    @Test
    public void createMune() throws IOException {
        ViewButton viewButton = new ViewButton();
        viewButton.setUrl("http://rv2vrw.natappfree.cc/api/index");
        viewButton.setName("测试授权");
        com.tinnkm.application.util.wechat.model.Menu menu = new com.tinnkm.application.util.wechat.model.Menu(viewButton);

        boolean mune = weChatUtils.createMenu(menu);
        assertTrue(mune);
    }
}