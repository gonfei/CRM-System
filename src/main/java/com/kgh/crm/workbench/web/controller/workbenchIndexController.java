package com.kgh.crm.workbench.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class workbenchIndexController {
    @RequestMapping("/workbench/index.do")
    public String index(){
        //跳转业务页面
        return "workbench/index";
    }
}