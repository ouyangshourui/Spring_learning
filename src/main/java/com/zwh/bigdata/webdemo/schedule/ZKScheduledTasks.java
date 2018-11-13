package com.zwh.bigdata.webdemo.schedule;

import com.zwh.bigdata.webdemo.hello.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ZKScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    @Autowired
    private ApplicationArguments applicationArguments;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

   // @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        RestTemplate restTemplate = new RestTemplate();
        Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        log.info(quote.toString());
        log.info("The time is now {} and thread id is {}", dateFormat.format(new Date()),Thread.currentThread().getId());
    }
}
