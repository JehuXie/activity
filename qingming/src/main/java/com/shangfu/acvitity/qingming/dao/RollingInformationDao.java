package com.shangfu.acvitity.qingming.dao;

import com.shangfu.acvitity.qingming.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author JehuXie
 * @Description: 轮播兑换信息
 * @date 2018/3/29 0029下午 4:19
 */
public interface RollingInformationDao extends JpaRepository<Address,Integer>{

    @Query(value = "select * from mfjc_address where id = :id",nativeQuery = true)
    Address findAddressById(@Param("id") int id);
}
