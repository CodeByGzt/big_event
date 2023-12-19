package com.gzt.controller;

import com.gzt.pojo.ResponseResult;
import com.gzt.pojo.User;
import com.gzt.pojo.vo.UserPwdVo;
import com.gzt.service.UserService;
import com.gzt.constans.SystemConstant;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: UserController
 * Package: com.gzt.controller
 * Description: 用户接口相关
 *
 * @Author gzt
 * @Create 12/10/2023 10:47 AM
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;
    // 修改密码
    @PatchMapping("/updatePwd")
    public ResponseResult updatePwd(@RequestBody @Validated UserPwdVo userPwdVo,
                                    @RequestHeader(name = "Authorization") String token){
        return userService.updatePwd(userPwdVo,token);
    }
    // 修改头像
    @PatchMapping("/updateAvatar")
    public ResponseResult  updateUserAvatar(@URL String avatarUrl){
        return userService.updateUserAvatar(avatarUrl);
    }

    // 修改用户信息
    @PutMapping("/update")
    public ResponseResult updateUserInfo(@RequestBody
                                         @Validated   User user){
        return userService.updateUserInfo(user);
    }

    // 获取当前登录用户信息
    @GetMapping("/userInfo")
    public ResponseResult<User> getUserInfo(){
        return userService.getUserInfo();
    }
    // 登录
    @PostMapping("/login")
    public ResponseResult<String> login(@Pattern(regexp = SystemConstant.LOGIN_REGEXP) String username, @Pattern(regexp = SystemConstant.LOGIN_REGEXP) String password){
        return userService.login(username,password);
    }
    // 注册
    @PostMapping("/register")
    public ResponseResult register(@Pattern(regexp = SystemConstant.LOGIN_REGEXP) String username, @Pattern(regexp = SystemConstant.LOGIN_REGEXP) String password) {
        return userService.register(username,password);
    }
}
