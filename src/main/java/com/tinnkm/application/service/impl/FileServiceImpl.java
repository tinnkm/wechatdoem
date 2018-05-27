package com.tinnkm.application.service.impl;

import com.tinnkm.application.dao.FileInfoDao;
import com.tinnkm.application.enums.FileType;
import com.tinnkm.application.model.FileInfo;
import com.tinnkm.application.service.FileService;
import com.tinnkm.application.util.file.FileUtils;
import com.tinnkm.application.util.result.Result;
import com.tinnkm.application.util.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Value("${spring.upload,basepath}")
    private String basePath;
    @Autowired
    private FileInfoDao fileInfoDao;
    @Override
    public Result upload(String bizId, FileType fileType, MultipartFile file) {
        Result upload = FileUtils.upload(basePath + File.separator + bizId + File.separator + fileType+ File.separator, file);
        if (upload.getCode() == ResultCode.Success){
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
                e.printStackTrace();
                return Result.error(e);
            }
        }else{
            return upload;
        }
    }

    @Override
    public Result delete(String fileId) {
        FileInfo file = fileInfoDao.findById(fileId).get();
        if (null == file){
            return Result.failed("传入信息有误！");
        }
        File saveFile = new File(basePath + file.getRelativePath() +File.separator+ file.getFileName());
        if (saveFile.exists()){
            saveFile.delete();
        }else{
            return Result.failed("文件不存在");
        }
        // 删除路径下的文件
        return fileInfoDao.deleteByFileId(fileId) > 0 ? Result.success() : Result.failed();
    }

    @Override
    public void download(String fileId, HttpServletResponse response) {
        FileInfo file = fileInfoDao.findById(fileId).get();
        FileUtils.download(basePath+file.getRelativePath(),file.getFileName(),response);
    }
}
