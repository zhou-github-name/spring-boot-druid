package com.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.springboot.common.entity.User;

/**
 * 后台管理用户表 Mapper
 *
 * @author 
 * @version 1.0
 * @since 
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}