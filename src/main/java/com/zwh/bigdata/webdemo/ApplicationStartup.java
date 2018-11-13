package com.zwh.bigdata.webdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ApplicationArguments applicationArguments;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("I am ContextRefreshedEvent *********************");
        System.out.println(System.getProperty("key"));
    }
}
