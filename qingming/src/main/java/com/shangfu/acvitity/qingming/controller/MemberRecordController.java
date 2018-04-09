package com.shangfu.acvitity.qingming.controller;

import com.google.gson.Gson;
import com.shangfu.acvitity.qingming.entity.JacksonUtil;
import com.shangfu.acvitity.qingming.entity.ResponseBean;
import com.shangfu.acvitity.qingming.service.MemberRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String findInfo(HttpServletRequest request,@RequestParam Object params) throws Exception {
        ResponseBean res = ResponseBean.getResponse();
        System.out.println("params = " + params.toString());
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> map = JacksonUtil.json2map(String.valueOf(params));
        for (String key : map.keySet()) {
            if("".equals(map.get(key)) || map.get(key)==null || "0".equals(map.get(key).toString())){
                /*result.put("code",400);
                result.put("message","参数为空");
                return new Gson().toJson(result);*/
                res = ResponseBean.setError("参数为空");
                return JacksonUtil.obj2json(res);
            }
        }
        System.out.println("map = " + map);
        String mobile_phone=map.get("mobile_phone").toString();
        System.out.println("mobile_phone = " + mobile_phone);
        boolean person = memberRecordService.findPerson(mobile_phone);
        if(person==true){

            res = ResponseBean.setError("特殊渠道用户不参与本次活动");
            return JacksonUtil.obj2json(res);
        }
        Date nowTime = new Date();
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
        int todaySignCount = memberRecordService.findToDaySignCount(time.format(nowTime));
        /*result.put("todaySignCount", todaySignCount);*/
        if(todaySignCount >= 100){
            /*result.put("code",400);
            result.put("message","今日红包已领完");
            return new Gson().toJson(result);*/
            res = ResponseBean.setError("今日红包已领完");
            return JacksonUtil.obj2json(res);
        }

        Map map1 = memberRecordService.sendMessage(mobile_phone);
        String ress=map1.get("res").toString();
        if(ress.equals("1000001")){
            /*result.put("code",400);
            result.put("message","今日红包已领取");
            return new Gson().toJson(result);*/
            res = ResponseBean.setError(map1.get("resMsg").toString());
            return JacksonUtil.obj2json(res);
        }
        String atime = String.valueOf(nowTime.getTime());
        int number = memberRecordService.insertInto_mfjc_activity_qingming_member_record(atime,mobile_phone);
        if(number==0){
            /*result.put("code",400);
            result.put("message","领取失败");*/
            res = ResponseBean.setError("领取失败");
            return JacksonUtil.obj2json(res);
        }
        /*result.put("code",200);
        result.put("message","领取成功");*/
        res = ResponseBean.setSuccess("领取成功");

        logger.info(result.toString());
        return JacksonUtil.obj2json(res);
}
}
