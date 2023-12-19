package com.gzt.exception;

import com.gzt.enums.AppHttpCodeEnum;

/**
 * ClassName: SystemException
 * Package: com.gzt.exception
 * Description: 统一异常处理
 *
 * @Author gzt
 * @Create 12/10/2023 4:53 PM
 * @Version 1.0
 */
public class SystemException extends RuntimeException {
    private int code;
    private String msg;
    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
    public SystemException(AppHttpCodeEnum appHttpCodeEnum) {
        super(appHttpCodeEnum.getMsg());
        this.code = appHttpCodeEnum.getCode();
        this.msg = appHttpCodeEnum.getMsg();
    }
}
