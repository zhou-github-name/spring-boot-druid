package com.springboot.controller;

//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.is;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.common.entity.User;
import com.springboot.service.UserService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 描述：
 *
 * @author zy
 */
@Slf4j
@Controller
public class DruidController {

	@Autowired
    private UserService userService;
	
	@ApiOperation(value = "获取helloWorld", notes = "简单SpringMVC请求")
	@RequestMapping(value = "/druidTest", method = RequestMethod.GET)
	@ResponseBody
	public void index() {
		 // 核心数据库中的用户id=1
        User scott1 = userService.findById(1);
        log.info("scott1:"+scott1.toString());
        //assertThat(user.getUsername(), is("scott_1"));
        
        // biz数据库中的用户id=1
        User scott2 = userService.findById1(1);
        log.info("scott2:"+scott2.toString());
        //assertThat(user1.getUsername(), is("scott_2"));
	}
}
