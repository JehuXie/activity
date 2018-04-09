package com.shangfu.acvitity.qingming.service;

import com.shangfu.acvitity.qingming.entity.Product;
import com.shangfu.acvitity.qingming.entity.Reciver;

import java.util.List;
import java.util.Map;

public interface MemberRecordService {
    /**
     * 查询今日已经签到完毕的人数
     *
     * @param today 查询时间格式 yyyy-mm-dd
     * @return count(*)
     */
    int findToDaySignCount(String today);

    /**
     * 通过手机号查询用户里程数
     * @param telphone  手机号
     * @return  里程数
     */
    int findMileageByTelphone(String telphone);

    /**
     * 新增用户-商品信息
     * @param receiver 用户总换商品信息
     * @return 插入状态
     */

    int insertProductAndMailage(Product product);

    /**
     * 将收件人信息保存到数据库
     * @param receiver 收件人
     * @return  插入状态
     */
    int insertReciver(Reciver receiver);

    /**
     * 查询获奖名单
     * @return 获奖人
     */
    List<String> findProduct();

    boolean findPerson(String phone);

    Map sendMessage(String mobilePhone);

    int insertInto_mfjc_activity_qingming_member_record(String create_time,String telphone);



}
