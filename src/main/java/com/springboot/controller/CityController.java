package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.springboot.common.entity.City;
import com.springboot.service.CityService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 城市
 */
@Controller
@Slf4j
public class CityController {
	@Autowired
	private CityService cityService;

	/**
	 * 银行销售人员创建城市下拉框查询
	 *
	 * @return
	 */
	@ApiOperation(value = "城市列表查询", notes = "查询")
	@PostMapping("/queryCityList")
	@ResponseBody
	public List<City> queryCityList() {
		List<City> queryCityList = cityService.queryCityList();
		log.info(JSONObject.toJSONString(queryCityList, SerializerFeature.WriteMapNullValue));
		return queryCityList;
	}

}
