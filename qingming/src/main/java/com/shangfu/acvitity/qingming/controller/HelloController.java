package com.shangfu.acvitity.qingming.controller;

import com.shangfu.acvitity.qingming.entity.Reciver;
import com.shangfu.acvitity.qingming.service.MemberRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class HelloController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private MemberRecordService memberRecordService;
    @RequestMapping("/hello")
    public String hello() {
        logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warn message");
        logger.error("This is an error message");
        return "hello,this is a springboot demo";
    }
}
