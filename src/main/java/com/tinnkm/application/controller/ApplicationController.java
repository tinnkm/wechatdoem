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

/**
 * @author tinnkm
 */
@RestController
@RequestMapping("/api")
public class ApplicationController {
    @Autowired
    private AuthService authService;

    @Autowired
    private ApprovalService approvalService;

    /**
     * 初始化审批记录
     * @param code 返回的code
     * @param state 需要传递的参数
     * @return 获取审批记录
     * @throws IOException
     * @throws URISyntaxException
     */
    @GetMapping("/createApproval")
    public Result<Approval> init(@SessionAttribute("code") String code,@SessionAttribute(value = "state",required = false) String state) throws IOException, URISyntaxException {
        Approval approval = authService.getAuth(code, state);
        return Result.success(approval);
    }

    /**
     * 根据业务id更新记录
     * @param bizId 业务id
     * @return json串
     */
    @PostMapping("/updateApproval/{bizId}")
    public Result update(@PathVariable("bizId") String bizId){
        return approvalService.update(bizId);
    }
}
