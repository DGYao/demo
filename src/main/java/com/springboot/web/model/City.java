package com.springboot.web.model;

import com.springboot.common.BaseModel;
import javax.persistence.*;

public class City extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 省id
     */
    @Column(name = "province_id")
    private Integer provinceId;

    /**
     * 城市名
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取省id
     *
     * @return province_id - 省id
     */
    public Integer getProvinceId() {
        return provinceId;
    }

    /**
     * 设置省id
     *
     * @param provinceId 省id
     */
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * 获取城市名
     *
     * @return name - 城市名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置城市名
     *
     * @param name 城市名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取描述
     *
     * @return description - 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     *
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description;
    }
}