package com.tinnkm.application.service.impl;

import com.tinnkm.application.ApplicationTests;
import com.tinnkm.application.model.ApprovalParams;
import com.tinnkm.application.service.ApprovalService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class ApprovalServiceImplTest extends ApplicationTests {

    @Autowired
    private ApprovalService approvalService;
    @Test
    public void getApprovalList() {
        approvalService.getApprovalList(new ApprovalParams());
    }
}