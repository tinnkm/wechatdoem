package com.tinnkm.application.service;

import com.tinnkm.application.enums.FileType;
import com.tinnkm.application.util.result.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public interface FileService {
    Result upload(String bizId, FileType fileType, MultipartFile file);
    Result delete(String fileId);

    void download(String fileId, HttpServletResponse response);
}
