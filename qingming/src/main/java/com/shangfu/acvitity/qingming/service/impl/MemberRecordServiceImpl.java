package com.shangfu.acvitity.qingming.service.impl;


import com.shangfu.acvitity.qingming.dao.MemberRecordDao;
import com.shangfu.acvitity.qingming.service.MemberRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberRecordServiceImpl implements MemberRecordService {
    @Autowired
    MemberRecordDao memberRecordDao;

    @Override
    public int findToDaySignCount(String today) {
        return memberRecordDao.findToDaySignCount(today);
    }
}
