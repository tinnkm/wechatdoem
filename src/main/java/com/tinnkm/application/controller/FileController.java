package com.tinnkm.application.controller;

import com.tinnkm.application.dao.FileInfoDao;
import com.tinnkm.application.enums.FileType;
import com.tinnkm.application.model.FileInfo;
import com.tinnkm.application.util.result.Result;
import com.tinnkm.application.util.file.FileUtils;
import com.tinnkm.application.util.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileController {
    @Value("${spring.upload,basepath}")
    private String basePath;
    @Autowired
    private FileInfoDao fileInfoDao;
    @PostMapping("/upload")
    public Result<String> upload(UUID bizId,FileType fileType, MultipartFile file){
        Result upload = FileUtils.upload(basePath + File.pathSeparator + bizId + File.pathSeparator + fileType, file);
        if (upload.getCode() == ResultCode.Success){
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileId(UUID.randomUUID());
            fileInfo.setBizId(bizId);
            fileInfo.setFileType(fileType);
            fileInfo.setRelativePath(File.pathSeparator + bizId + File.pathSeparator + fileType);
            try {
                fileInfoDao.saveAndFlush(fileInfo);
                return Result.success("上传成功");
            } catch (Exception e) {
                e.printStackTrace();
                return Result.error(e);
            }
        }else{
            return upload;
        }

    }
}
