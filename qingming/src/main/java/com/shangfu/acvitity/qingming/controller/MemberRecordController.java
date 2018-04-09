package com.shangfu.acvitity.qingming.controller;

import com.google.gson.Gson;
import com.shangfu.acvitity.qingming.service.MemberRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MemberRecordController {
    @Autowired
    private MemberRecordService memberRecordService;
    private final Logger logger = LoggerFactory.getLogger(MemberRecordController.class);

    @RequestMapping(value = "/Qingming/findInfo", method = RequestMethod.POST)
    public String findInfo(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        Date nowTime = new Date();
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
        int todaySignCount = memberRecordService.findToDaySignCount(time.format(nowTime));
        result.put("todaySignCount", todaySignCount);
        logger.info(result.toString());
        return new Gson().toJson(result);
    }
}
