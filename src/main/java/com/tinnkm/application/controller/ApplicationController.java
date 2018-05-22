package com.tinnkm.application.controller;

import com.tinnkm.application.model.Approval;
import com.tinnkm.application.service.AuthService;
import com.tinnkm.application.util.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class ApplicationController {
    @Autowired
    private AuthService authService;
    @GetMapping("/index")
    public String index() throws IOException, URISyntaxException {
        boolean auth = authService.getAuth("www.baidu.com", "1");
        if (auth){
            return "index";
        }
        return "failed";
    }

    @GetMapping("/createApproval")
    public Result<Approval> init(){
        // 获取流程
        return Result.success();
    }
}
