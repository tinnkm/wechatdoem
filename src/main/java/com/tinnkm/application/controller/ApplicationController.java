package com.tinnkm.application.controller;

import com.tinnkm.application.model.Approval;
import com.tinnkm.application.service.AuthService;
import com.tinnkm.application.util.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class ApplicationController {
    @Autowired
    private AuthService authService;


    @GetMapping("/createApproval")
    public Result<Approval> init(@SessionAttribute("code") String code,@SessionAttribute("state") String state) throws IOException, URISyntaxException {
        Approval approval = authService.getAuth(code, state);
        // 获取流程
        return Result.success(approval);
    }
}
