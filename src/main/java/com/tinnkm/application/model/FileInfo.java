package com.tinnkm.application.model;

import com.tinnkm.application.enums.FileType;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

/**
 * 用户上传文件类
 * 同一bizid和openid和filetype下只允许存在一条信息
 */
@Entity
public class FileInfo {
    @Id
    private String fileId;
    // 业务id
    private String bizId;
    // 文件存放的相对路径
    private String relativePath;
    private FileType fileType;
    private String fileName;
    //region getter/setter
    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    //endregion
}
