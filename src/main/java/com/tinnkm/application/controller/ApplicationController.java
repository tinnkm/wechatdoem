package com.tinnkm.application.controller;

import com.tinnkm.application.model.Approval;
import com.tinnkm.application.service.ApprovalService;
import com.tinnkm.application.service.AuthService;
import com.tinnkm.application.util.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ApplicationController {
    @Autowired
    private AuthService authService;

    @Autowired
    private ApprovalService approvalService;

//     fixed this is for test,remember change it ro above
    @GetMapping("/createApproval")
    public Result<Approval> init(@SessionAttribute("code") String code,@SessionAttribute(value = "state",required = false) String state) throws IOException, URISyntaxException {
        Approval approval = authService.getAuth(code, state);
//        Approval approval = new Approval();
//        approval.setStatus(0);
//        approval.setBizId("WX"+new Date().getTime());
//        approval.setOpenId("...");
        // 获取流程
        return Result.success(approval);
    }
    @PostMapping("/updateApproval/{bizId}")
    public Result update(@PathVariable("bizId") String bizId){
        return approvalService.update(bizId);
    }
}
