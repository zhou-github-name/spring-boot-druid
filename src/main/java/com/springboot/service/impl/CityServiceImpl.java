package com.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.common.entity.City;
import com.springboot.datasource.annotion.DataSourceMore;
import com.springboot.datasource.mutidatesource.DSEnum;
import com.springboot.mapper.CityMapper;
import com.springboot.service.CityService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class CityServiceImpl implements CityService {

	@Autowired
	private CityMapper cityMapper;

	/**
	 * 银行销售人员创建城市下拉框查询
	 *
	 * @return
	 */
	@DataSourceMore(name = DSEnum.DATA_SOURCE_SQLSERVER)
	public List<City> queryCityList() {
		List<City> cityList = null;
		try {
			cityList = cityMapper.selectList(null);
		} catch (Exception e) {
			log.error("银行销售人员创建城市下拉框查询异常", e);
		}
		return cityList;
	}

}
