package com.springboot.task;

import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 描述：Spring Scheduled示例
 *
 * @author zy
 */
@Slf4j
@Component
@DisallowConcurrentExecution
public class ScheduledTask extends QuartzJobBean {
	
	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		long start = System.currentTimeMillis();
		log.info("任务1开始-------------------->{}", new Date());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			log.info("等待异常-------------------->{}", e.getMessage());
		}
		// log.info("任务结束-------------------->{}", new Date());
		long end = System.currentTimeMillis();
		log.info("任务1结束-------------------->耗时:{}", (end - start) + "ms");
	}
	
}
