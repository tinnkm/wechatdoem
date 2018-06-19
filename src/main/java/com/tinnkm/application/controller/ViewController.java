package com.tinnkm.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author tinnkm
 */
@Controller
public class ViewController {

    @GetMapping("/auth")
    public String auth() {
        return "auth";
    }
    @RequestMapping("/index")
    public String index(String code, String state, HttpSession session) {
        // 授权回调之后生成对应信息
        session.setAttribute("code",code);
        session.setAttribute("state",state);
        return "index";
    }
}
