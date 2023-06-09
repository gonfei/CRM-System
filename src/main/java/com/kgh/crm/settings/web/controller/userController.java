package com.kgh.crm.settings.web.controller;

import com.kgh.crm.commons.constants.Constants;
import com.kgh.crm.commons.domain.returnObject;
import com.kgh.crm.commons.utils.DateUtils;
import com.kgh.crm.settings.domain.User;
import com.kgh.crm.settings.service.userService;
import org.aspectj.apache.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Controller
public class userController {

    @Autowired
    private userService userService;

    @RequestMapping("/settings/qx/user/toLogin.do")
    public String toLogin() {
        return "settings/qx/user/login";
    }

    @RequestMapping("/settings/qx/user/login.do")
    public @ResponseBody
    Object login(String loginAct, String loginPwd, String isRemPwd, HttpServletRequest request, HttpSession session) {

        //封装参数
        HashMap<String, Object> objectObjectHashMap = new HashMap<String, Object>();
        objectObjectHashMap.put("loginAct", loginAct);
        objectObjectHashMap.put("loginPwd", loginPwd);
        //查询用户
        User user = userService.queryUserByLoginActAndPwd(objectObjectHashMap);
        System.out.println(user);
        //生成响应信息
        returnObject retObj = new returnObject();
        if (user == null) {
            //登录失败
            retObj.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            retObj.setMsg("用户名或密码错误");

        } else {
            //查出来，则需要进一步判断账号是否合法

            //判断是否符合过期时间
            if (DateUtils.formatDateTime(new Date()).compareTo(user.getExpireTime()) > 0) {
                //账号过期
                retObj.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                retObj.setMsg("账号过期");
            } else if ("0".equals(user.getLockState())) {
                //账号锁定
                retObj.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                retObj.setMsg("账号锁定");
            } else if (!user.getAllowIps().contains(request.getRemoteAddr())) {
                //ip不合法
                retObj.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                retObj.setMsg("ip不合法");
            } else {
                //登录成功
                retObj.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
                //将用户的信息存储到session中
                session.setAttribute(Constants.SESSION_USER, user);
            }
        }
        return retObj;
    }
}
