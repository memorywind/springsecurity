package com.itheima.springsecurity.service.impl;

import com.itheima.springsecurity.config.DBUserDetailsManager;
import com.itheima.springsecurity.entity.User;
import com.itheima.springsecurity.mapper.UserMapper;
import com.itheima.springsecurity.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wanghao
 * @since 2026-03-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private DBUserDetailsManager dbUserDetailsManager;

    @Override
    public void saveUserDetail(User user) {
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withDefaultPasswordEncoder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
        dbUserDetailsManager.createUser(userDetails);
    }
}
