package com.tinnkm.application.util.wechat;

import com.tinnkm.application.ApplicationTests;
import com.tinnkm.application.enums.WeChatMuneType;
import com.tinnkm.application.model.Menu;
import com.tinnkm.application.model.MenuGroup;
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
        ArrayList<Menu> menus = new ArrayList<>();
//        Menu mune1 = new Menu();
//        mune1.setName("扫码");
//        ArrayList<Menu> submune1 = new ArrayList<>();
//        Menu mune2 = new Menu();
//        mune2.setName("扫码带提示");
//        mune2.setType(WeChatMuneType.SCANCODE_WAITMSG.getValue());
//        submune1.add(mune2);
//        mune1.setSubButton(submune1);
//        menus.add(mune1);
        Menu menu3 = new Menu();
        menu3.setName("百度");
        menu3.setType(WeChatMuneType.VIEW.getValue());
        menu3.setUrl("https://www.baidu.com");
        menus.add(menu3);
        MenuGroup menuGroup = new MenuGroup(menus);
        boolean mune = weChatUtils.createMenu(menuGroup);
        assertTrue(mune);
    }
}