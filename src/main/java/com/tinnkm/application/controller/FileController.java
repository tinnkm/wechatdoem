package com.tinnkm.application.controller;

import com.tinnkm.application.enums.FileType;
import com.tinnkm.application.service.FileService;
import com.tinnkm.application.util.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;

/**
 * @author tinnkm
 */
@RestController
@RequestMapping("/api/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload/{fileType}")
    public Result<String> upload(String bizId, @PathVariable("fileType") FileType fileType, MultipartFile file) {
        return fileService.upload(bizId, fileType, file);
    }
    @GetMapping("/download/{fileId}")
    public void download(@PathVariable("fileId") String fileId, HttpServletResponse response) throws FileNotFoundException {
        fileService.download(fileId, response);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") String fileId) {
        return fileService.delete(fileId);
    }
}
