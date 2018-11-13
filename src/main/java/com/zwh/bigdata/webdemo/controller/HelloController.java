package com.zwh.bigdata.webdemo.controller;

import com.zwh.bigdata.webdemo.hello.Greeting;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.atomic.AtomicLong;

@Controller
public class HelloController {
    @RequestMapping("/")
    @ResponseBody
    String index() {
        return "Hello World!";
    }


    private static final String template = "Hello, %s!";

    private final AtomicLong counter = new AtomicLong();

    /**
     * http://127.0.0.1:8081/hello-world?name=ourui
     * @param name
     * @return
     */
    @GetMapping("/hello-world")
    @ResponseBody
    public Greeting sayHello(@RequestParam(name="name", required=false, defaultValue="Stranger") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/hello-world1")
    @ResponseBody
    public Greeting sayHello1(@RequestParam(name="name", required=false, defaultValue="Stranger") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }


}
