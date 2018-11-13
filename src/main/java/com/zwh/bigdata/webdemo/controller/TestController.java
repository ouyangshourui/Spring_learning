package com.zwh.bigdata.webdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @Autowired
    private ApplicationArguments applicationArguments;
    @RequestMapping("/test")
    @ResponseBody
    String index() {
        System.out.println(applicationArguments.getNonOptionArgs());
        return "Hello World!test";
    }

}
