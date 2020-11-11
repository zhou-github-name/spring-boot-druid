package com.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.springboot.common.entity.City;

/**
 * 提供城市下拉框查询
 */
@Mapper
public interface CityMapper extends BaseMapper<City> {

}
