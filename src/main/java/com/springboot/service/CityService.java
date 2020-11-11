package com.springboot.service;

import java.util.List;

import com.springboot.common.entity.City;
import com.springboot.datasource.annotion.DataSourceMore;
import com.springboot.datasource.mutidatesource.DSEnum;

public interface CityService {
	
	@DataSourceMore(name = DSEnum.DATA_SOURCE_SQLSERVER)
    public List<City> queryCityList();

}
