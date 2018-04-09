package com.shangfu.acvitity.qingming.service.impl;

import com.shangfu.acvitity.qingming.dao.RollingInformationDao;
import com.shangfu.acvitity.qingming.entity.Address;
import com.shangfu.acvitity.qingming.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author JehuXie
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/3/29 0029下午 4:54
 */
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    RollingInformationDao rollingInformationDao;
    @Override
    public Address findAddressById(int id) {
        return rollingInformationDao.findAddressById(id);
    }
}
