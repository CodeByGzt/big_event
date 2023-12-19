package com.gzt.pojo;



import com.alibaba.fastjson.annotation.JSONField;
import com.gzt.constans.SystemConstant;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    @NotNull
    private Integer id;//主键ID
    private String username;//用户名
    @JSONField(serialize = false)
    private String password;//密码
    @NotNull
    @Pattern(regexp = SystemConstant.NICK_NAME_REGEXP)
    private String nickname;//昵称
    @Email
    private String email;//邮箱
    private String userPic;//用户头像地址
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
