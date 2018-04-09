package com.shangfu.acvitity.qingming.dao;

import com.shangfu.acvitity.qingming.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDao extends JpaRepository<User,Long> {

    //查询标和投入的钱
    @Query(value = "select b.id,c.period,b.total_money from mfjc_member a INNER JOIN mfjc_order b ON b.member_id=a.id INNER JOIN mfjc_subject c ON b.subject_id=c.id AND b.create_time > '1522771200000' and b.create_time < '1523203200000' and a.mobile_phone=:phone", nativeQuery = true)
    @Modifying
    List<User> getTotal(@Param("phone") String phone);



}
