package com.demo.service;

import com.demo.dao.UserDao;
import com.demo.domain.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserDao userDao;

    public User getUser(){
        return userDao.getUser();
    }
}
