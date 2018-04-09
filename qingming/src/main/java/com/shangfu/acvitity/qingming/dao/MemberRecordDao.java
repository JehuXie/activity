package com.shangfu.acvitity.qingming.dao;

import com.shangfu.acvitity.qingming.entity.MemberRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRecordDao extends JpaRepository<MemberRecord, Long> {
    /**
     * 查询今日已经签到完毕的人数
     * @param today 查询时间格式 yyyy-mm-dd
     * @return count(*)
     */
    @Query(value = "SELECT count(*) FROM mfjc_activity_qingming_member_record WHERE FROM_UNIXTIME(create_time / 1000, '%Y-%m-%d')=:today", nativeQuery = true)
    int findToDaySignCount(@Param("today") String today);


}
