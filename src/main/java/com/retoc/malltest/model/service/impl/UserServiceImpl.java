package com.retoc.malltest.model.service.impl;

import com.retoc.malltest.model.dao.UserMapper;
import com.retoc.malltest.model.pojo.User;
import com.retoc.malltest.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUser() {
        return userMapper.selectByPrimaryKey(1);
    }
}
