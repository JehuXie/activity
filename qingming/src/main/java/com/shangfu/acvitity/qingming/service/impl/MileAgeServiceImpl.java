package com.shangfu.acvitity.qingming.service.impl;

import com.shangfu.acvitity.qingming.dao.MileAgeDao;
import com.shangfu.acvitity.qingming.entity.User;
import com.shangfu.acvitity.qingming.service.MileAgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author JehuXie
 * @Description: ${todo}
 * @date 2018/3/30 0030上午 11:00
 */
@Service
public class MileAgeServiceImpl implements MileAgeService {
    private final static int NUMBER = 100;
    @Autowired
    MileAgeDao mileAgeDao;
    @Override
    public int findMileAgeByPhone(String phone) {
        //BigDecimal mileAge = new BigDecimal(50);
        int mileAge = 0;

        List<User> users = mileAgeDao.findMileAgeByPhone(phone);

        for (User user:users) {
            switch (user.getPeriod()){
                case 1:
                    mileAge +=  user.getTotal_money().intValue()/NUMBER*1;
                    break;
                case 2:
                    mileAge +=  user.getTotal_money().intValue()/NUMBER*1.5;
                    break;
                case 3:
                    mileAge +=  user.getTotal_money().intValue()/NUMBER*2;
                    break;
            }
            /*if(user.getPeriod()==1){
                mileAge +=  user.getTotal_money().intValue()/NUMBER*1;
            }else if(user.getPeriod()==2){
                mileAge +=  user.getTotal_money().intValue()/NUMBER*1.5;
            }else if(user.getPeriod()==3){
                mileAge +=  user.getTotal_money().intValue()/NUMBER*2;
            }*/
            System.out.println("========================="+user.getTotal_money()+"======================");
        }
        System.out.println(mileAge);
        return mileAge;

    }
}
