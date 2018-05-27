package com.tinnkm.application.dao;


import com.tinnkm.application.model.Approval;
import com.tinnkm.application.model.FileInfo;
import com.tinnkm.application.util.jpa.interfaces.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


public interface ApprovalDao extends BaseRepository<Approval,String> {
    @Modifying
    @Transactional()
    Approval saveAndFlush(Approval approval);
    @Modifying
    @Transactional()
    int updateSelective(Approval approval);
    Approval findByBizId(String bizId);
    Approval findByOpenIdAndDeleted(String openId,Integer deleted);
}
