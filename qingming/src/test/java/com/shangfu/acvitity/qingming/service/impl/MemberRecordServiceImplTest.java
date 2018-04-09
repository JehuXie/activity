package com.shangfu.acvitity.qingming.service.impl;

import com.shangfu.acvitity.qingming.service.MemberRecordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author JehuXie
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/4/2 0002上午 9:43
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRecordServiceImplTest {

    @Autowired
    private MemberRecordService memberRecordService;


    @Test
    public void findMileageByTelphone() {
        int res = memberRecordService.findMileageByTelphone("13372557429");
        System.out.println(res);
    }
    @Test
    public void findToDaySignCount(){
       int a = memberRecordService.findToDaySignCount("2018-04-03");
        System.out.println(a);
        /*Date nowTime = new Date();
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(time.format(nowTime));*/
    }

}