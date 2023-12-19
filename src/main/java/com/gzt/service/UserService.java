package com.gzt.service;

import com.gzt.pojo.ResponseResult;
import com.gzt.pojo.User;
import com.gzt.pojo.vo.UserPwdVo;

/**
 * ClassName: UserService
 * Package: com.gzt.service
 * Description:
 *
 * @Author gzt
 * @Create 12/10/2023 10:50 AM
 * @Version 1.0
 */
public interface UserService {
    ResponseResult register(String username, String password);

    ResponseResult<String> login(String username, String password);

    ResponseResult<User> getUserInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult updateUserAvatar(String avatarUrl);

    ResponseResult updatePwd(UserPwdVo userPwdVo,String token);
}
