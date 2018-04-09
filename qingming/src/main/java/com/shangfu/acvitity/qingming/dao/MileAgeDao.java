package com.shangfu.acvitity.qingming.dao;

import com.shangfu.acvitity.qingming.entity.User;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author JehuXie
 * @Description: ${todo}
 * @date 2018/3/30 0030上午 10:49
 */
@Repository
public interface MileAgeDao extends JpaRepository<User,Integer>{

    @Query(value = "select b.id,c.period,b.total_money from mfjc_member a INNER JOIN mfjc_order b ON b.member_id=a.id " +
            "INNER JOIN mfjc_subject c ON b.subject_id=c.id and a.mobile_phone=:phone",nativeQuery = true)
    @Modifying
    List<User> findMileAgeByPhone(@Param("phone") String phone);
}
