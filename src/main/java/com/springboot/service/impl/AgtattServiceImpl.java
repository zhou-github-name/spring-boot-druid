package com.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.common.entity.AADAGTATT;
import com.springboot.datasource.annotion.DataSourceMore;
import com.springboot.datasource.mutidatesource.DSEnum;
import com.springboot.mapper.AgtattMapper;
import com.springboot.service.AgtattService;

@Service
@Transactional
public class AgtattServiceImpl implements AgtattService {

	@Autowired
	private AgtattMapper agtattMapper;

	@Override
	@DataSourceMore(name = DSEnum.DATA_SOURCE_DBTO)
	public List<AADAGTATT> queryAgtattList() {
		return agtattMapper.selectList(null);
	}

}
