package com.shangfu.acvitity.qingming.service;

public interface MemberRecordService {
    /**
     * 查询今日已经签到完毕的人数
     *
     * @param today 查询时间格式 yyyy-mm-dd
     * @return count(*)
     */
    int findToDaySignCount(String today);
}
