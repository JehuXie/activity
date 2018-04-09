package com.shangfu.acvitity.qingming.dao;

import com.shangfu.acvitity.qingming.entity.Product;
import com.shangfu.acvitity.qingming.entity.User;
import com.shangfu.acvitity.qingming.service.MemberRecordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductDaoTest {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private MemberRecordService memberRecordService;

    @Autowired
    private UserDao userDao;

    @Test
    public void getShangping() {
        List<Product> list = productDao.getShangping();
        for(Product a : list){
            System.out.println(a.getTelphone()+a.getProduct()+a.getMileage());
        }
    }

    @Test
    public void getmile() {
        int a = productDao.getmile("18912201020");
        System.out.println(a);
    }

    @Test
    public void getTotal(){
        List<User> users = userDao.getTotal("13372557429");
        for(User user:users){
            System.out.println(user.getPeriod()+" "+user.getTotal_money());
        }
    }
    @Test
    public void insertInto_q_product(){
        int a = productDao.insertInto_q_product("13173666030","尿不湿",2000);
        System.out.println(a);
    }

    @Test
    public void insertInto_q_rec(){
        int a =productDao.insertInto_q_rec("何锦昊","13173666030","浙江诸暨阮市镇");
        System.out.println(a);
    }



    @Test
    public void getmileages(){
        List<String> s = memberRecordService.findProduct();
        System.out.println(s);
    }


}