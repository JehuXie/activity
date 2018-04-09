package com.shangfu.acvitity.qingming.controller;

import com.shangfu.acvitity.qingming.entity.JacksonUtil;
import com.shangfu.acvitity.qingming.entity.ResponseBean;
import com.shangfu.acvitity.qingming.service.MemberRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JehuXie
 * @Description: ${todo}
 * @date 2018/3/30 0030上午 11:02
 */
@Api(value="里程数")
@RestController
public class MileAgeController {
    @Autowired
    MemberRecordService memberRecordService;
    @ApiOperation(value="根据用户手机号计算用户里程数", notes="")
    @PostMapping(value = "/getMileAge",produces = "application/json;charset=UTF-8")
    //@ApiImplicitParam(name = "params", value = "用户手机号", required = true, dataType = "String")
    public String findMileAgeByPhone(HttpServletRequest request,@RequestParam Object params) throws Exception {

        //Object params = request.getParameter("param");
        Map<String, Object> paramsMap = JacksonUtil.json2map(String.valueOf(params));
        ResponseBean<Integer> res = ResponseBean.getResponse();
        for (String str:paramsMap.keySet()) {
            if("".equals(paramsMap.get(str))){
                res = ResponseBean.setError("手机号为空");
                return JacksonUtil.obj2json(res);
            }
        }
        String mobile_phone = paramsMap.get("mobile_phone").toString();
        try {
            int mileAge = memberRecordService.findMileageByTelphone(mobile_phone);
            res = ResponseBean.setSuccess("获取里程数成功");
            res.setValue(mileAge);
        } catch (Exception e) {
            e.printStackTrace();
            res = ResponseBean.setError("获取里程数失败");
        }
        return JacksonUtil.obj2json(res);
    }
}
