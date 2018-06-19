package com.tinnkm.application.dao;

import com.tinnkm.application.enums.FileType;
import com.tinnkm.application.model.FileInfo;
import com.tinnkm.application.util.jpa.interfaces.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author tinnkm
 */
public interface FileInfoDao extends BaseRepository <FileInfo,String>{
    /**
     * 保存实体
     * @param fileInfo 需保存的实体
     * @return 保存过后的实体
     */
    @Override
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    FileInfo saveAndFlush(FileInfo fileInfo);

    /**
     * 消极更新
     * @param fileInfo 需更新的实体
     * @return 返回影响行数
     */
    @Override
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    int updateSelective(FileInfo fileInfo);

    /**
     * 根据业务id获取文件信息
     * @param bizId 业务id
     * @return 文件集合
     */
    List<FileInfo> findByBizId(String bizId);

    /**
     * 根据业务id和文件类型获取文件
     * @param bizId 业务id
     * @param fileType 文件类型
     * @return 文件
     */
    FileInfo findByBizIdAndFileType(String bizId,  FileType fileType);

    /**
     * 删除文件信息
     * @param fileId 文件id
     * @return 返回影响行数
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    int deleteByFileId(String fileId);
}
