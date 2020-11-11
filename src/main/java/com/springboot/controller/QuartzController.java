package com.springboot.controller;

import com.springboot.task.ScheduledTask;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 描述：
 *
 * @author zy
 */
@Slf4j
@Controller
public class QuartzController {

	@Autowired
	private Scheduler scheduler;

	@ApiOperation(value = "测试Quartz", notes = "简单的Quartz请求")
	@RequestMapping(value = "/quartz", method = RequestMethod.GET)
	@ResponseBody
	public void index() {
		// cron表达式
		//CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/8 * * * * ?");
		// 根据name 和group获取当前trgger 的身份
		TriggerKey triggerKey = TriggerKey.triggerKey("zy", "123");
		CronTrigger triggerOld = null;
		try {
			// 注入到容器当中的Scheduler 在初始化的时候clear（）一下就好了
			scheduler.clear();
			// 获取触发器的信息
			triggerOld = (CronTrigger) scheduler.getTrigger(triggerKey);
		} catch (SchedulerException e) {
			log.error("获取触发器异常", e);
		}
		if (triggerOld == null) {
			// 将job加入到jobDetail中
			//JobDetail jobDetail = JobBuilder.newJob(ScheduledTask.class).build();
			//Trigger trigger = TriggerBuilder.newTrigger().withSchedule(cronScheduleBuilder).build();
			JobDetail jobDetail=JobBuilder.newJob(ScheduledTask.class).withIdentity("myJob", "group1").build();
			Date date = new Date();
			//获取距离当前时间3秒后的时间
			date.setTime(date.getTime()+3000);
			Date endDate = new Date();
			//获取距离当前时间3秒后的时间
			endDate.setTime(endDate.getTime()+6000);
			//创建一个Trigger实例，定义该Job3秒后执行，并且每隔两秒钟重复执行一次，6秒后停止
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger", "group1")//定义name/group
								.startAt(date)   //设置开始时间
								//.endAt(endDate)	 //设置结束时间 
								.withSchedule(SimpleScheduleBuilder.simpleSchedule() //使用SimpleTrigger
										.withIntervalInSeconds(5) //每隔两秒执行一次
										.repeatForever() //一直执行，直到程序死去
										//.withRepeatCount(3) //重复执行三次
										).build();
			// 执行任务
			try {
				scheduler.scheduleJob(jobDetail, trigger);
			} catch (SchedulerException e) {
				log.error("执行任务异常", e);
			}
		} else {
			System.out.println("当前job已存在--------------------------------------------");
		}
	}
}
