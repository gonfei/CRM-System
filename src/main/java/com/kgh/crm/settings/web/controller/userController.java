package com.kgh.crm.settings.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class userController {
    @RequestMapping("/settings/qx/user/login.do")
    public String toLogin() {
        return "settings/qx/user/login";
    }
}
