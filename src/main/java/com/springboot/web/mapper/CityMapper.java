package com.springboot.web.mapper;

import com.springboot.common.BaseMapper;
import com.springboot.web.model.City;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface CityMapper extends BaseMapper<City> {
    @Select("select * from city where name=#{name}")
    List<City> findByName(String name);
}