package com.shangfu.acvitity.qingming.controller;

import com.shangfu.acvitity.qingming.entity.Address;
import com.shangfu.acvitity.qingming.entity.JacksonUtil;
import com.shangfu.acvitity.qingming.entity.ResponseBean;
import com.shangfu.acvitity.qingming.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JehuXie
 * @Description: ${todo}
 * @date 2018/3/29 0029下午 4:55
 */
@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;
    @GetMapping("/address")
    @ResponseBody
    public String findAddressById() throws Exception{
        ResponseBean<Address> res = ResponseBean.getResponse();
        Address address = null;
        try {
            address = addressService.findAddressById(4);
            System.out.println(address.getProvince());
            res.setValue(address);

        } catch (Exception e) {
            e.printStackTrace();
            res = ResponseBean.setError("数据获取失败");
        }
        return JacksonUtil.obj2json(res);
    }
}
