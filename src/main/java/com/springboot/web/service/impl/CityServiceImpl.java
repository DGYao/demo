package com.springboot.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.springboot.web.mapper.CityMapper;
import com.springboot.web.model.City;
import com.springboot.web.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 城市Service实现
 * Created by Administrator on 2017/5/11.
 */
@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityMapper cityMapper;

    @Override
    public List<City> findByName(String name) {
        return cityMapper.findByName(name);
    }

    @Override
    public List<City> findList(Integer page, Integer rows) {
        if (page == null && rows == null) {
            page = rows = 1;
        }
        PageHelper.startPage(page, rows);
        return cityMapper.selectAll();
    }

    @Override
    public City findById(Integer id) {
        return cityMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(Integer id) {
        cityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void addOrUpdate(City city) {
        if (city.getId() == null) {
            cityMapper.insertSelective(city);
        } else {
            cityMapper.updateByPrimaryKeySelective(city);
        }
    }
}
