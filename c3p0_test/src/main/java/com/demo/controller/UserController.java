package com.demo.controller;


import com.demo.domain.User;
import com.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/admin_user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/getuser")
    @ResponseBody
    public Object getUser(){
        User user = userService.getUser();
        System.out.println("user ="+ user);
        return user;
    }
}
