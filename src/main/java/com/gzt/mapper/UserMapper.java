package com.gzt.mapper;

import com.gzt.pojo.User;
import com.gzt.pojo.vo.UserPwdVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * ClassName: UserMapper
 * Package: com.gzt.mapper
 * Description:
 *
 * @Author gzt
 * @Create 12/10/2023 10:51 AM
 * @Version 1.0
 */
@Mapper
public interface UserMapper {
    // 通过用户名查找
    @Select("select * from user where username = #{username}")
    User findUserByName(String username);

    // 新增
    @Insert("INSERT INTO user(username, password,create_time, update_time) VALUES " +
            "(#{username}, #{password},now(),now())")
    int addUser(String username, String password);

    // 修改用户信息
    @Update("update user set nickname = #{nickname},email = #{email},update_time = now() where id= #{id}")
    void updateUserInfo(User user);


    // 修改用户头像
    @Update("update user set user_pic = #{avatarUrl},update_time = now() where id= #{id}")
    void updateUserAvatar(String avatarUrl, Integer id);


    // 修改用户密码
    @Update("update user set password = #{newPwd},update_time = now() where id= #{id}")
    void updateUserPwd(String newPwd, Integer id);
}
