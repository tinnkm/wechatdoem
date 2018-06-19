package com.tinnkm.application.service.impl;

import com.tinnkm.application.dao.FileInfoDao;
import com.tinnkm.application.enums.FileType;
import com.tinnkm.application.model.FileInfo;
import com.tinnkm.application.service.FileService;
import com.tinnkm.application.util.file.FileUtils;
import com.tinnkm.application.util.result.Result;
import com.tinnkm.application.util.result.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * @author tinnkm
 */
@Service
public class FileServiceImpl implements FileService {
    private final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
    @Value("${spring.upload,basepath}")
    private String basePath;
    @Autowired
    private FileInfoDao fileInfoDao;
    @Override
    public Result upload(String bizId, FileType fileType, MultipartFile file) {
        Result upload = FileUtils.upload(basePath + File.separator + bizId + File.separator + fileType+ File.separator, file);
        if (upload.getCode() == ResultCode.SUCCESS){
            UUID id = UUID.randomUUID();
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileId(id.toString());
            fileInfo.setBizId(bizId);
            fileInfo.setFileType(fileType);
            fileInfo.setFileName(file.getOriginalFilename());
            fileInfo.setRelativePath(File.separator + bizId + File.separator + fileType);
            try {
                fileInfoDao.saveAndFlush(fileInfo);
                return Result.success("上传成功",id);
            } catch (Exception e) {
                logger.error(e.getLocalizedMessage());
                return Result.error(e);
            }
        }else{
            return upload;
        }
    }

    @Override
    public Result delete(String fileId) {
        FileInfo file = fileInfoDao.findById(fileId).orElse(null);
        if (null == file){
            return Result.failed("传入信息有误！");
        }
        try {
            Files.delete(Paths.get(basePath + file.getRelativePath() +File.separator+ file.getFileName()));
        } catch (IOException e) {
            return Result.failed("文件不存在");
        }
        // 删除路径下的文件
        return fileInfoDao.deleteByFileId(fileId) > 0 ? Result.success() : Result.failed();
    }

    @Override
    public void download(String fileId, HttpServletResponse response) throws FileNotFoundException {
        FileInfo file = fileInfoDao.findById(fileId).orElse(null);
        if (null == file){
            throw new FileNotFoundException();
        }
        FileUtils.download(basePath+file.getRelativePath(),file.getFileName(),response);
    }
}
