package com.gzt.controller;

import com.gzt.pojo.ResponseResult;
import com.gzt.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName: FileUploadController
 * Package: com.gzt.controller
 * Description:
 *
 * @Author gzt
 * @Create 12/12/2023 2:41 PM
 * @Version 1.0
 */
@RestController
@RequestMapping("/upload")
public class FileUploadController {
    @Autowired
    private UploadService uploadService;

    // 上传七牛云oss
    @PostMapping
    public ResponseResult ossUpload(MultipartFile file){
        return uploadService.ossUpload(file);
    }
}
