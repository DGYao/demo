package com.springboot.web.service;

import com.springboot.web.model.City;

import java.util.List;

/**
 * 城市service
 * Created by Administrator on 2017/5/11.
 */
public interface CityService {
    /**
     * 通过城市名查找城市
     * @param name 城市名
     * @return
     */
    List<City> findByName(String name);

    /**
     * 查找城市列表
     * @return
     * @param page
     * @param rows
     */
    List<City> findList(Integer page, Integer rows);

    /**
     * 查找城市
     * @param id
     * @return
     */
    City findById(Integer id);

    /**
     *删除城市
     * @param id
     */
    void delete(Integer id);

    /**
     * 新增或更新城市
     * @param city
     */
    void addOrUpdate(City city);
}
