package com.shangfu.acvitity.qingming.controller;

import com.shangfu.acvitity.qingming.entity.JacksonUtil;
import com.shangfu.acvitity.qingming.entity.ResponseBean;
import com.shangfu.acvitity.qingming.service.MemberRecordService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.List;

/**
 * @author JehuXie
 * @Description: 头部轮播用户信息
 * @date 2018/3/30 0030下午 3:03
 */
@RestController
public class RollingInformationController {
    @Autowired
    MemberRecordService memberRecordService;

    @ApiOperation(value="获取头部用户兑换信息", notes="")
    @GetMapping("/getRollingInformation")
    public String getRollingInformation() throws Exception {
        ResponseBean res = ResponseBean.getResponse();

        try {
            List<String> lists = memberRecordService.findProduct();
            res = ResponseBean.setSuccess("获取信息成功");
            res.setValue(lists);
        } catch (Exception e) {
            e.printStackTrace();
            res = ResponseBean.setError("获取信息失败");
        }
        return JacksonUtil.obj2json(res);
    }
}
