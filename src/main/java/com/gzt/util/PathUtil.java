package com.gzt.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * ClassName: PathUtil
 * Package: com.gzt.util
 * Description:
 *
 * @Author gzt
 * @Create 12/12/2023 2:50 PM
 * @Version 1.0
 */
public class PathUtil {

    public static String generateFilePath(String fileName){
        //根据日期生成路径   2022/1/15/
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        String datePath = sdf.format(new Date());
        //uuid作为文件名
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //后缀和文件后缀一致
        int index = fileName.lastIndexOf(".");
        // test.jpg -> .jpg
        String fileType = fileName.substring(index);
        // 自己加了一层文件放在放在项目目录下
        return new StringBuilder().append("big-event/").append(datePath).append(uuid).append(fileType).toString();
    }
}
