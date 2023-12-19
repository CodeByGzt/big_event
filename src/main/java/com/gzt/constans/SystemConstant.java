package com.gzt.constans;

/**
 * ClassName: ConstantUtils
 * Package: com.gzt.util
 * Description:
 *
 * @Author gzt
 * @Create 12/10/2023 4:20 PM
 * @Version 1.0
 */
public class SystemConstant {
    // 数字0
    public static final  int ZERO_NUMBER = 0;

    // 5~16位非空字符的匹配规则
    public static final  String LOGIN_REGEXP = "^\\S{5,16}$";
    public static final  String NICK_NAME_REGEXP = "^\\S{1,10}$";
}
