package com.kgh.crm.settings.service.Impl;

import com.kgh.crm.settings.domain.User;
import com.kgh.crm.settings.mapper.UserMapper;
import com.kgh.crm.settings.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("userService")
public class userServiceImpl implements userService {

    @Autowired
    private UserMapper userMapper;

    public User queryUserByLoginActAndPwd(Map<String, Object> map) {
        return userMapper.selectUserByLoginActAndPwd(map);
    }
}
