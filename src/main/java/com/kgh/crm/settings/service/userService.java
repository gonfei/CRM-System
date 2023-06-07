package com.kgh.crm.settings.service;

import com.kgh.crm.settings.domain.User;

import java.util.Map;

public interface userService {
    User queryUserByLoginActAndPwd(Map<String, Object> map);
}
