package com.gzt.service.impl;

import com.google.gson.Gson;
import com.gzt.enums.AppHttpCodeEnum;
import com.gzt.exception.SystemException;
import com.gzt.pojo.ResponseResult;
import com.gzt.service.UploadService;
import com.gzt.util.PathUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * ClassName: UploadServiceImpl
 * Package: com.gzt.service.impl
 * Description:
 *
 * @Author gzt
 * @Create 12/12/2023 2:46 PM
 * @Version 1.0
 */
@Service
@Data
@ConfigurationProperties(prefix = "oss")
public class UploadServiceImpl implements UploadService {

    // oss文件上传
    @Override
    public ResponseResult ossUpload(MultipartFile file) {
        // 判断文件类型
        // 获取原始文件名
        String originalFilename = file.getOriginalFilename();
        // 对原始文件名进行判断
        // 如果不是png和jpg文件报错
        if(!originalFilename.endsWith(".png") &&  !originalFilename.endsWith(".jpg")){
            throw new SystemException(AppHttpCodeEnum.FILE_TYPE_ERROR);
        }
        //如果判断通过上传文件到OSS
        String filePath = PathUtil.generateFilePath(originalFilename);
        String url = uploadOss(file,filePath);//  2099/2/3/wqeqeqe.png
        return ResponseResult.okResult(url);
    }
    private String accessKey;
    private String secretKey;
    private String bucket;

    private String domain;


    private String uploadOss(MultipartFile imgFile, String filePath){
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = filePath;
        try {
            InputStream inputStream = imgFile.getInputStream();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(inputStream,key,upToken,null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                return domain + key;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception ex) {
            //ignore
        }
        return "www";
    }
}
