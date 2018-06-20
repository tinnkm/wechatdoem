package com.tinnkm.application.service.impl;

import com.tinnkm.application.ApplicationTests;
import com.tinnkm.application.model.ApprovalParams;
import com.tinnkm.application.service.ApprovalService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.Date;

import static org.junit.Assert.*;

public class ApprovalServiceImplTest extends ApplicationTests {

    @Autowired
    private ApprovalService approvalService;
    @Test
    public void getApprovalList() {
        ApprovalParams approvalParams = new ApprovalParams();
        approvalParams.setBizIdLike("1");
        approvalParams.setCreateTimeAfter(new Date());
        approvalParams.setCreateTimeBefore(new Date());
        approvalParams.setStatus("1");
        approvalService.getApprovalList(approvalParams,PageRequest.of(1,10));
    }
}