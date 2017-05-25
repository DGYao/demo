package com.springboot.web.mapper;

import com.springboot.common.BaseMapper;
import com.springboot.web.model.UUserRole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

public interface UUserRoleMapper extends BaseMapper<UUserRole> {
}