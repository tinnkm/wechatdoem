package com.tinnkm.application.controller;

import com.tinnkm.application.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URISyntaxException;

@Controller
public class viewContriller {

    @GetMapping("/auth")
    public String auth() throws IOException, URISyntaxException {
        return "auth";
    }
    @RequestMapping("/index")
    public String index(String code, String state, HttpSession session) throws IOException, URISyntaxException {
        // 授权回调之后生成对应信息
        session.setAttribute("code",code);
        session.setAttribute("state",state);
        return "index";
    }
}
