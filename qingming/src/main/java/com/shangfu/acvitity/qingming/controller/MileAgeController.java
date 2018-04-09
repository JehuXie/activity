package com.shangfu.acvitity.qingming.controller;

import com.shangfu.acvitity.qingming.entity.JacksonUtil;
import com.shangfu.acvitity.qingming.entity.ResponseBean;
import com.shangfu.acvitity.qingming.service.MileAgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JehuXie
 * @Description: ${todo}
 * @date 2018/3/30 0030上午 11:02
 */
@RestController
public class MileAgeController {
    @Autowired
    MileAgeService mileAgeService;
    @GetMapping("/getMileAge")
    @ResponseBody
    public String findMileAgeByPhone(String phone) throws Exception {
        ResponseBean<Integer> res = ResponseBean.getResponse();
        try {
            int mileAge = mileAgeService.findMileAgeByPhone("13372557429");
            res.setValue(mileAge);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JacksonUtil.obj2json(res);
    }
}
