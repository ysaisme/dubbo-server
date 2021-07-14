package com.ysa.model.base;

import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Author: ysd
 * @Description: 通用mapper，所用的dao都要继承这个接口
 * @Date: Created in 2021/7/14 12:19
 * Modified By:
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T>, BaseMapper<T> {
}
