package com.tinnkm.application.dao;


import com.tinnkm.application.model.Approval;
import com.tinnkm.application.util.jpa.interfaces.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author tinnkm
 */
public interface ApprovalDao extends BaseRepository<Approval,String> {
    /**
     * 保存并刷新实体对象
     * @param approval 要更新的实体
     * @return 更新的实体
     */
    @Override
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    Approval saveAndFlush(Approval approval);

    /**
     * 消极的更新
     * @param approval 需更新实体
     * @return  影响行数
     */
    @Override
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    int updateSelective(Approval approval);

    /**
     * 根据业务id获取实体对象
     * @param bizId 业务id
     * @return 实体对象
     */
    Approval findByBizId(String bizId);

    /**
     * 根据openid和是否删除获取实体
     * @param openId openid
     * @param deleted 是否删除
     * @return 获取的实体
     */
    Approval findByOpenIdAndDeleted(String openId,Integer deleted);
}
