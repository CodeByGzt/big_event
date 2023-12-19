package com.gzt.pojo.vo;

import com.gzt.constans.SystemConstant;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * ClassName: UserPwdVo
 * Package: com.gzt.pojo.vo
 * Description: 用户修改密码的视图
 *
 * @Author gzt
 * @Create 12/11/2023 11:21 AM
 * @Version 1.0
 */
@Data
public class UserPwdVo {

    // 旧密码
    @NotNull
    @Pattern(regexp = SystemConstant.LOGIN_REGEXP)
    private String oldpwd;
    // new_pwd
    @NotNull
    @Pattern(regexp = SystemConstant.LOGIN_REGEXP)
    private String newpwd;
    // 确认密码
    @NotNull
    @Pattern(regexp = SystemConstant.LOGIN_REGEXP)
    private String repwd;
}
