package com.springboot.common;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 通用mapper
 * Created by ez on 2017/5/11.
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
