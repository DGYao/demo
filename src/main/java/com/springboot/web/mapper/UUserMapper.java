package com.springboot.web.mapper;

import com.springboot.common.BaseMapper;
import com.springboot.web.model.UUser;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface UUserMapper extends BaseMapper<UUser> {
    List<UUser> selectByMap(Map<String, Object> map);

    @Select("select * from u_user where nickname=#{username}")
    UUser findByName(String username);
}