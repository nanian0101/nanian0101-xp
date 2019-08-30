package com.xp.ssm.service;

import com.xp.ssm.entity.User;

public interface IUserService {

    /**
     * 根据手机号查找用户信息
     * @param phone 用户手机
     * @return 用户信息
     */
    User selectByPhone(String phone,String password);

    /**
     * 根据用户id查找用户信息
     * @param uid 用户手机
     * @return 用户信息
     */
    User selectByUid(Integer uid);

    /**
     * 新增用户
     * @param user 用户信息
     * @return 受影响行数
     */
    void InsertUser(User user);

    /**
     * 修改用户资料
     * @param user 用户信息
     * @return 受影响行数
     */
    void UpdateUser(User user);

    /**
     * redis string 存和取测试
     * @param key
     * @return
     */
    String getString(String key);
}
