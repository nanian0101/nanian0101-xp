package com.xp.ssm.mapper;

import com.xp.ssm.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    /**
     * 根据手机号查找用户信息
     * @param phone 用户手机
     * @return 用户信息
     */
    @Select("SELECT * FROM t_user WHERE phone = #{phone}")
    User findByPhone(String phone);

    /**
     * 根据用户名查找用户信息
     * @param username 用户手机
     * @return 用户信息
     */
    @Select("SELECT * FROM t_user WHERE username = #{username}")
    User findByUserNama(String username);

    /**
     * 根据邮箱查找用户信息
     * @param email 用户手机
     * @return 用户信息
     */
    @Select("SELECT * FROM t_user WHERE email = #{email}")
    User findByEmail(String email);

    /**
     * 根据用户id查找用户信息
     * @param uid 用户手机
     * @return 用户信息
     */
    @Select("SELECT uid,username,avatar,phone,email,gender,resume,create_time AS createTime," +
            " modified_user AS modifiedUser, modified_time AS modifiedTime  FROM t_user WHERE uid = #{uid}")
    User findByUid(Integer uid);

    /**
     * 新增用户
     * @param user 用户信息
     * @return 受影响行数
     */
    Integer addUser(User user);

    /**
     * 修改用户资料
     * @param user 用户信息
     * @return 受影响行数
     */
    Integer changeUser(User user);
}
