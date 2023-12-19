package com.gzt.service;

import com.gzt.pojo.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName: UploadService
 * Package: com.gzt.service
 * Description:
 *
 * @Author gzt
 * @Create 12/12/2023 2:46 PM
 * @Version 1.0
 */
public interface UploadService {
    ResponseResult ossUpload(MultipartFile file);
}
