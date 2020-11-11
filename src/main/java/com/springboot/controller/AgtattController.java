package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.springboot.common.entity.AADAGTATT;
import com.springboot.service.AgtattService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AgtattController {
	
	@Autowired
	private AgtattService agtattService;

	@ApiOperation(value = "数据列表查询", notes = "查询")
	@PostMapping("/queryAgtattList")
	@ResponseBody
	public List<AADAGTATT> queryAgtattList() {
		List<AADAGTATT> agtattList = agtattService.queryAgtattList();
		log.info(JSONObject.toJSONString(agtattList, SerializerFeature.WriteMapNullValue));
		return agtattList;
	}

}
