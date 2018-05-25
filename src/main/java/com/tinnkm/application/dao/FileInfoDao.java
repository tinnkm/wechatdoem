package com.tinnkm.application.dao;

import com.tinnkm.application.enums.FileType;
import com.tinnkm.application.model.FileInfo;
import com.tinnkm.application.util.jpa.interfaces.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


public interface FileInfoDao extends BaseRepository <FileInfo,UUID>{
    @Modifying
    @Transactional
    FileInfo saveAndFlush(FileInfo fileInfo);
    @Modifying
    @Transactional
    int updateSelective(FileInfo fileInfo);
    List<FileInfo> findByBizId(UUID bizId);
    FileInfo findByBizIdAndFileType(UUID bizId,  FileType fileType);
    @Modifying
    @Transactional
    int deleteByFileId(UUID fileId);
}
