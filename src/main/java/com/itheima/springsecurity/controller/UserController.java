package com.itheima.springsecurity.controller;

import com.itheima.springsecurity.entity.User;
import com.itheima.springsecurity.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    public UserService userService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('USR_LIST')")
    public List<User> getList(){
        return userService.list();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN') and authentication.name == 'admin'")
    public void addUser(@RequestBody User user){
        userService.saveUserDetail(user);
    }
}
