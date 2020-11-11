package com.springboot.datasource.config;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSource;

import lombok.Data;

@Component
@Data
public class DataSourceConfig {

	/*
	 * 定义初始连接数
	 */
	@Value("${spring.datasource.druid.initial-size}")
	private Integer initialSize;

	/*
	 * 最小空闲数
	 */
	@Value("${spring.datasource.druid.min-idle}")
	private Integer minIdle;

	/*
	 * 最大连接数
	 */
	@Value("${spring.datasource.druid.max-active}")
	private Integer maxActive;

	/*
	 * 获取连接等待超时的时间
	 */
	@Value("${spring.datasource.druid.max-wait}")
	private Integer maxWait;

	/*
	 * 登陆超时时间
	 */
	@Value("${spring.datasource.druid.login-timeout}")
	private Integer loginTimeout;

	/*
	 * 查询超时时间
	 */
	@Value("${spring.datasource.druid.query-timeout}")
	private Integer queryTimeout;

	/*
	 * 事务查询超时时间
	 */
	@Value("${spring.datasource.druid.transaction-query-timeout}")
	private Integer transactionQueryTimeout;

	/*
	 * 默认只读设置
	 */
	@Value("${spring.datasource.druid.default-read-only}")
	private Boolean defaultReadOnly;

	/*
	 * 默认事务隔离
	 */
	// @Value("${spring.datasource.druid.default-transaction-isolation}")
	// private Integer defaultTransactionIsolation;

	/*
	 * 超过时间限制是否回收
	 */
	@Value("${spring.datasource.druid.remove-abandoned}")
	private Boolean removeAbandoned;

	/*
	 * 超过时间限制多长
	 */
	@Value("${spring.datasource.druid.time-between-eviction-runs-millis}")
	private Integer removeAbandonedTimeout;

	/*
	 * 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
	 */
	@Value("${spring.datasource.druid.time-between-eviction-runs-millis}")
	private Integer timeBetweenEvictionRunsMillis;

	/*
	 * 配置一个连接在池中最小生存的时间，单位是毫秒
	 */
	@Value("${spring.datasource.druid.min-evictable-idle-time-millis}")
	private Integer minEvictableIdleTimeMillis;

	/*
	 * 用来检测连接是否有效的sql，要求是一个查询语句
	 */
	@Value("${spring.datasource.druid.validation-query}")
	private String validationQuery;

	/*
	 * 申请连接的时候检测
	 */
	@Value("${spring.datasource.druid.test-while-idle}")
	private Boolean testWhileIdle;

	/*
	 * 申请连接时执行validationQuery检测连接是否有效，配置为true会降低性能
	 */
	@Value("${spring.datasource.druid.test-on-borrow}")
	private Boolean testOnBorrow;

	/*
	 * 归还连接时执行validationQuery检测连接是否有效，配置为true会降低性能
	 */
	@Value("${spring.datasource.druid.test-on-return}")
	private Boolean testOnReturn;

	/*
	 * 打开PSCache，并且指定每个连接上PSCache的大小
	 */
	@Value("${spring.datasource.druid.pool-prepared-statements}")
	private Boolean poolPreparedStatements;

	@Value("${spring.datasource.druid.max-pool-prepared-statement-per-connection-size}")
	private Integer maxPoolPreparedStatementPerConnectionSize;

	/*
	 * 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙,配置多个英文逗号分隔
	 */
	@Value("${spring.datasource.druid.filters}")
	private String filters;

	public void config(DruidDataSource dataSource) {

		dataSource.setInitialSize(initialSize); // 定义初始连接数
		dataSource.setMinIdle(minIdle); // 最小空闲数
		dataSource.setMaxActive(maxActive); // 定义最大连接数
		dataSource.setMaxWait(maxWait); // 获取连接等待超时的时间
		dataSource.setLoginTimeout(loginTimeout);// 登陆超时时间
		dataSource.setQueryTimeout(queryTimeout);// 查询超时时间
		dataSource.setTransactionQueryTimeout(transactionQueryTimeout);// 事务查询超时时间
		dataSource.setDefaultReadOnly(defaultReadOnly);// 默认只读设置
		// dataSource.setDefaultTransactionIsolation(defaultTransactionIsolation);//
		// 默认事务隔离
		dataSource.setRemoveAbandoned(removeAbandoned); // 超过时间限制是否回收
		dataSource.setRemoveAbandonedTimeout(removeAbandonedTimeout); // 超过时间限制多长

		// 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
		dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		// 配置一个连接在池中最小生存的时间，单位是毫秒
		dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		// 用来检测连接是否有效的sql，要求是一个查询语句
		dataSource.setValidationQuery(validationQuery);
		// 申请连接的时候检测
		dataSource.setTestWhileIdle(testWhileIdle);
		// 申请连接时执行validationQuery检测连接是否有效，配置为true会降低性能
		dataSource.setTestOnBorrow(testOnBorrow);
		// 归还连接时执行validationQuery检测连接是否有效，配置为true会降低性能
		dataSource.setTestOnReturn(testOnReturn);
		// 打开PSCache，并且指定每个连接上PSCache的大小
		dataSource.setPoolPreparedStatements(poolPreparedStatements);
		dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
		// 属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：
		// 监控统计用的filter:stat
		// 日志用的filter:log4j
		// 防御SQL注入的filter:wall
		try {
			dataSource.setFilters(filters);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
