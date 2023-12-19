package com.gzt.service.impl;

import com.gzt.enums.AppHttpCodeEnum;
import com.gzt.exception.SystemException;
import com.gzt.mapper.UserMapper;
import com.gzt.pojo.ResponseResult;
import com.gzt.pojo.User;
import com.gzt.pojo.vo.UserPwdVo;
import com.gzt.service.UserService;
import com.gzt.util.JwtUtil;
import com.gzt.util.Md5Util;
import com.gzt.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: UserServiceImpl
 * Package: com.gzt.service.impl
 * Description:
 *
 * @Author gzt
 * @Create 12/10/2023 10:50 AM
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // 修改密码

    @Override
    public ResponseResult updatePwd(UserPwdVo userPwdVo,String token) {
        Map<String,Object> userInfo = ThreadLocalUtil.get();
        // 获取登录用户信息
        User user = findUserByName((String) userInfo.get("username"));
        // 判断旧密码是否正确，以及了两次输入的密码是否相同
        if (!Md5Util.checkPassword(userPwdVo.getOldpwd(),user.getPassword())){
            throw new SystemException(AppHttpCodeEnum.LOGIN_ERROR);
        }
        if (!userPwdVo.getNewpwd().equals(userPwdVo.getRepwd())){
            throw new SystemException(AppHttpCodeEnum.PASSWORD_DIFFERENT);
        }
        // 与修改用户信息类似
        // 需要传入当前用户id
        userMapper.updateUserPwd(Md5Util.getMD5String(userPwdVo.getRepwd()),(Integer) userInfo.get("id"));
        // 删除Redis中的token
        stringRedisTemplate.opsForValue().getOperations().delete(token);
        return ResponseResult.okResult();
    }

    // 修改用户头像
    @Override
    public ResponseResult updateUserAvatar(String avatarUrl) {
        // 与修改用户信息类似
        // 需要传入当前用户id
        Map<String,Object> userInfo = ThreadLocalUtil.get();
        userMapper.updateUserAvatar(avatarUrl,(Integer) userInfo.get("id"));
        return ResponseResult.okResult();
    }

    // 修改用户信息
    @Override
    public ResponseResult updateUserInfo(User user) {
        // 判断是否当前用户
        //  自己修改自己的 管理员有权修改所有
        // 从ThreadLocal拿到数据
        Map<String,Object> userInfo = ThreadLocalUtil.get();
        if (userInfo.get("id") == user.getId() || userInfo.get("username") == "admin"){
            // 修改
            userMapper.updateUserInfo(user);
            return ResponseResult.okResult();
        }
        // 无权限
        throw new SystemException(AppHttpCodeEnum.NO_OPERATOR_AUTH);
    }

    // 获取当前登录用户信息
    @Override
    public ResponseResult<User> getUserInfo() {
        // 从ThreadLocal拿到数据
        Map<String,Object> userInfo = ThreadLocalUtil.get();
        // 返回数据
        return ResponseResult.okResult(findUserByName((String) userInfo.get("username")));
    }

    // 登录
    @Override
    public ResponseResult<String> login(String username, String password) {
        User loginUser = findUserByName(username);
        // 用户是否存在
        if ( loginUser == null){
            return  ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR);
        }
        // 校验密码
        if (Md5Util.checkPassword(password,loginUser.getPassword())){
            // 返回jwt令牌
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",loginUser.getId());
            claims.put("username",loginUser.getUsername());
            String token = JwtUtil.genToken(claims);
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            // 放入redis一份
            operations.set(token,token,12, TimeUnit.HOURS);
            return ResponseResult.okResult(token);
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR);
    }

    // 注册
    @Override
    public ResponseResult register(String username, String password) {
        // 通过用户名查询
        if (findUserByName(username) != null){
            return ResponseResult.errorResult(AppHttpCodeEnum.USERNAME_EXIST);
        }else {
            // 注册，密码加密
            return userMapper.addUser(username,Md5Util.getMD5String(password)) > 0? ResponseResult.okResult(): ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }

    }

    // 通过用户名查询
    private User findUserByName(String username) {
        return userMapper.findUserByName(username);
    }
}
