package com.shangfu.acvitity.qingming.controller;

import com.shangfu.acvitity.qingming.entity.JacksonUtil;
import com.shangfu.acvitity.qingming.entity.Product;
import com.shangfu.acvitity.qingming.entity.Reciver;
import com.shangfu.acvitity.qingming.entity.ResponseBean;
import com.shangfu.acvitity.qingming.service.MemberRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jehription: 兑换商品
 * @date 2018/3/30 0030下午 3:12
 */
@Api(value="兑换商品")
@RestController
public class ExchangeProductController {
    @Autowired
    MemberRecordService memberRecordService;
    @ApiOperation(value="根据里程数兑换商品", notes="")
    @PostMapping("/exchangeProduct")
    @Transactional
    public String exchangeProduct(@ApiParam(value = "mobile_phone,user_phone,user_address,real_name,goods_type") @RequestParam Object params) throws Exception {
        ResponseBean res = ResponseBean.getResponse();

        Map<String, Object> map = JacksonUtil.json2map(String.valueOf(params));

        for (String key : map.keySet()) {
            if("".equals(map.get(key)) || map.get(key)==null || "0".equals(map.get(key).toString())){
                res = ResponseBean.setError("参数为空");
                return JacksonUtil.obj2json(res);
            }
        }
        String phone = map.get("mobile_phone").toString();
        String user_phone = map.get("user_phone").toString();
        String address = map.get("user_address").toString();
        String name = map.get("real_name").toString();
        Integer goods_type = (Integer) map.get("goods_type");

        Product product = new Product();
        Reciver reciver = new Reciver();
        switch (goods_type){
            case 1:
                product.setProduct("拍立得相机");
                product.setMileage(6000);
                break;
            case 2:
                product.setProduct("春游帐篷");
                product.setMileage(3000);
                break;
            case 3:
                product.setProduct("野餐防潮垫");
                product.setMileage(1500);
                break;
            case 4:
                product.setProduct("手机充电宝");
                product.setMileage(800);
                break;
            case 5:
                product.setProduct("便当盒");
                product.setMileage(500);
                break;
        }
        product.setTelphone(phone);
        reciver.setAddress(address);
        reciver.setName(name);
        reciver.setTelphone(user_phone);
        boolean person = memberRecordService.findPerson(phone);
        if(person==true){

            res = ResponseBean.setError("特殊渠道用户不参与本次活动");
            return JacksonUtil.obj2json(res);
        }

        int mile = memberRecordService.findMileageByTelphone(phone);
        if(mile-product.getMileage()<0){
            res = ResponseBean.setError("兑换失败,里程数不足！");
            return JacksonUtil.obj2json(res);
        }

        try {
            int flag = memberRecordService.insertProductAndMailage(product);
            int flag1 = memberRecordService.insertReciver(reciver);
            if(flag==1 && flag1==1){
                res = ResponseBean.setSuccess("兑换成功！");
            }else {
                res = ResponseBean.setError("兑换失败！");
            }

        } catch (Exception e) {
            e.printStackTrace();
            res = ResponseBean.setError("兑换失败！");
        }
        return JacksonUtil.obj2json(res);
    }
}
