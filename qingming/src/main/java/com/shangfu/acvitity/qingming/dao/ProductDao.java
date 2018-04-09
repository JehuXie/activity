package com.shangfu.acvitity.qingming.dao;

import com.shangfu.acvitity.qingming.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Long> {

    //获取领取了奖励的用户名单
    @Query(value = "select * from q_product",nativeQuery = true)
    @Modifying
    List<Product> getShangping();

    //获取已经瑞环的总里程
    @Query(value = "select SUM(mileage) from q_product where telphone = :phone", nativeQuery = true)
    int getmile(@Param("phone") String phone);

    //插入商品的里程
    @Modifying
    @Transactional
    @Query(value = "insert into q_product (telphone,product,mileage) values (:telphone,:product,:mileage)",nativeQuery = true)
    int insertInto_q_product(@Param("telphone") String telphone,@Param("product") String product,@Param("mileage") int mileage);


    //插入商品收货地址
    @Modifying
    @Transactional
    @Query(value = "INSERT  INTO q_rec (name,telphone,address) VALUES (:name,:telphone,:address)",nativeQuery = true)
    int insertInto_q_rec(@Param("name") String name,@Param("telphone") String telphone,@Param("address") String address);

    //插入今日已领取人数
    @Modifying
    @Transactional
    @Query(value = "INSERT  INTO mfjc_activity_qingming_member_record (create_time,mobile_phone) VALUES (:create_time,:mobile_phone)",nativeQuery = true)
    int insertInto_mfjc_activity_qingming_member_record(@Param("create_time") String create_time,@Param("mobile_phone") String telphone);

}
