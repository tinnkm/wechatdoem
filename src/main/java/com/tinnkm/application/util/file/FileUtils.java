package com.tinnkm.application.util.file;

import com.tinnkm.application.util.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author tinnkm
 */
public class FileUtils {
    private static final Logger log = LoggerFactory.getLogger(FileUtils.class);

    /**
     * 文件上传
     * @param path 上传路径
     * @param file 文件
     * @return 上传结果
     */
    public static Result upload(String path, MultipartFile file) {
        if (file.isEmpty()) {
            return Result.failed("文件为空");
        }
        String originalFilename = file.getOriginalFilename();
        log.info("the file name is {}", originalFilename);
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        log.info("the suffix is {}", suffix);
        File dest = new File(path + originalFilename);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            log.info("upload done!");
            return Result.success();
        } catch (IOException e) {
            log.error("the upload error!because {}", e.getMessage());
            return Result.error(e);
        }

    }

    /**
     * 文件下载
     * @param path 文件路径
     * @param fileName 文件名
     * @param response 返回对象
     */
    public static void download(String path, String fileName, HttpServletResponse response) {
        File file = new File(path + File.separator + fileName);
        if (file.exists()) {
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
            byte[] bytes = new byte[1024];
            try (FileInputStream fis = new FileInputStream(file); BufferedInputStream bis = new BufferedInputStream(fis); OutputStream os = response.getOutputStream()) {
                int i = bis.read(bytes);
                while (i != -1){
                    os.write(bytes);
                    i = bis.read(bytes);
                }
                log.info("download complete");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            log.info("the file not exists");
        }
    }
}
