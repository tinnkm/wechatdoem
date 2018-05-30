package com.tinnkm.application.service;

import com.tinnkm.application.enums.FileType;
import com.tinnkm.application.util.result.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.util.UUID;

/**
 * @author tinnkm
 */
public interface FileService {
    /**
     * 更新文件信息
     * @param bizId 业务id
     * @param fileType 文件类型
     * @param file 文件
     * @return 更新结果
     */
    Result upload(String bizId, FileType fileType, MultipartFile file);

    /**
     * 删除文件
     * @param fileId 文件id
     * @return 删除结果
     */
    Result delete(String fileId);

    /**
     * 文件下载
     * @param fileId 文件id
     * @param response 返回的response
     * @throws FileNotFoundException 文件未找到
     */
    void download(String fileId, HttpServletResponse response) throws FileNotFoundException;
}
