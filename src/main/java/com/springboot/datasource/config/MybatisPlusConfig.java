package com.springboot.datasource.config;

import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;
import com.springboot.datasource.mutidatesource.DSEnum;
import com.springboot.datasource.mutidatesource.DynamicDataSource;
import com.springboot.datasource.properties.CoreProperties;
import com.springboot.datasource.properties.DBToProperties;
import com.springboot.datasource.properties.ScottProperties;
import com.springboot.datasource.properties.SqlServerProperties;

@Configuration
@EnableTransactionManagement(order = 3)
public class MybatisPlusConfig {

	@Autowired
	private CoreProperties coreConfig;
	@Autowired
	private ScottProperties scottConfig;
	@Autowired
	private SqlServerProperties sqlServerConfig;
	@Autowired
	private DBToProperties dbToProperties;

	/**
	 * 核心数据源
	 */
	private DruidDataSource coreDataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		System.out.println("core：" + coreConfig.toString());
		coreConfig.config(dataSource);
		return dataSource;
	}

	/**
	 * 测试数据源
	 */
	private DruidDataSource scottDataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		System.out.println("scott：" + scottConfig.toString());
		scottConfig.config(dataSource);
		return dataSource;
	}

	/**
	 * Sql Server数据源
	 */
	private DruidDataSource sqlServerDataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		System.out.println("sqlServer：" + sqlServerConfig.toString());
		sqlServerConfig.config(dataSource);
		return dataSource;
	}

	/**
	 * DB2数据源
	 */
	private DruidDataSource dbToDataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		System.out.println("dbToProperties：" + dbToProperties.toString());
		dbToProperties.config(dataSource);
		return dataSource;
	}

	/**
	 * 单数据源连接池配置
	 */
	@Bean
	@ConditionalOnProperty(prefix = "xncoding", name = "muti-datasource-open", havingValue = "false")
	public DruidDataSource singleDatasource() {
		return coreDataSource();
	}

	/**
	 * 多数据源连接池配置
	 * 
	 * @throws SQLException
	 */
	@Bean
	@ConditionalOnProperty(prefix = "xncoding", name = "muti-datasource-open", havingValue = "true")
	public DynamicDataSource mutiDataSource() throws Exception {
		DruidDataSource coreDataSource = coreDataSource();
		DruidDataSource scottDataSource = scottDataSource();
		DruidDataSource sqlServerDataSource = sqlServerDataSource();
		DruidDataSource dbToDataSource = dbToDataSource();
		try {
			coreDataSource.init();
		} catch (SQLException sql) {
			System.err.println("主数据源初始化异常" + sql.getMessage());
			sql.printStackTrace();
			throw sql;
		}
		try {
			scottDataSource.init();
		} catch (SQLException sql) {
			System.err.println("测试数据源初始化异常" + sql.getMessage());
			sql.printStackTrace();
			throw sql;
		}
		try {
			sqlServerDataSource.init();
		} catch (SQLException sql) {
			System.err.println("SqlServer数据源初始化异常" + sql.getMessage());
			sql.printStackTrace();
			throw sql;
		}
		try {
			dbToDataSource.init();
		} catch (SQLException sql) {
			System.err.println("DB2数据源初始化异常" + sql.getMessage());
			sql.printStackTrace();
			throw sql;
		}
		DynamicDataSource dynamicDataSource = new DynamicDataSource();
		HashMap<Object, Object> hashMap = new HashMap<>();
		hashMap.put(DSEnum.DATA_SOURCE_CORE, coreDataSource);
		hashMap.put(DSEnum.DATA_SOURCE_SCOTT, scottDataSource);
		hashMap.put(DSEnum.DATA_SOURCE_SQLSERVER, sqlServerDataSource);
		hashMap.put(DSEnum.DATA_SOURCE_DBTO, dbToDataSource);
		dynamicDataSource.setTargetDataSources(hashMap);
		dynamicDataSource.setDefaultTargetDataSource(coreDataSource);
		return dynamicDataSource;
	}
}
