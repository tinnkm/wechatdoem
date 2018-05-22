package com.tinnkm.application.dao;

import com.tinnkm.application.enums.FileType;
import com.tinnkm.application.model.FileInfo;
import com.tinnkm.application.util.jpa.interfaces.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


public interface FileInfoDao extends BaseRepository <FileInfo,UUID>{
    FileInfo saveAndFlush(FileInfo fileInfo);
    int updateSelective(FileInfo fileInfo);
    List<FileInfo> findByBizId(UUID bizId);
    FileInfo findByBizIdAndFileType(UUID bizId,  FileType fileType);
}
