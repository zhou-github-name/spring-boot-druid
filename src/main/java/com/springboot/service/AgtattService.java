package com.springboot.service;

import java.util.List;

import com.springboot.common.entity.AADAGTATT;
import com.springboot.datasource.annotion.DataSourceMore;
import com.springboot.datasource.mutidatesource.DSEnum;

public interface AgtattService {

	/**
	 * 查询全部数据
	 * @return
	 */
	@DataSourceMore(name = DSEnum.DATA_SOURCE_DBTO)
	public List<AADAGTATT> queryAgtattList();
	
}
