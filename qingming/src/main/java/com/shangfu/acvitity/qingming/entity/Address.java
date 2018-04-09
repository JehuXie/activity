package com.shangfu.acvitity.qingming.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author JehuXie
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/3/29 0029下午 4:46
 */
@Data
@Entity(name = "mfjc_address")
public class Address {
    @Id
    private int id;
    @Column(name = "province")
    private String province;
    @Column(name = "city")
    private String city;
    @Column(name = "district")
    private String district;
    @Column(name = "bm")
    private int bm;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getBm() {
        return bm;
    }

    public void setBm(int bm) {
        this.bm = bm;
    }
}
