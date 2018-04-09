package com.shangfu.acvitity.qingming.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity(name = "mfjc_activity_qingming_member_record")
public class MemberRecord {
    @Id
    @GeneratedValue
    private Integer id;
    // 领取礼包的手机号码
    @Column(nullable = false, length = 11, name = "mobile_phone")
    private String mobilePhone;
    // 记录类型
    @Column(length = 1)
    private Integer type;
    @Column(nullable = false, name = "create_time")
    private String createTime;
}
