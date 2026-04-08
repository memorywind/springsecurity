package com.itheima.springsecurity.service;

import com.itheima.springsecurity.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wanghao
 * @since 2026-03-15
 */
public interface UserService extends IService<User> {

    void saveUserDetail(User user);
}
