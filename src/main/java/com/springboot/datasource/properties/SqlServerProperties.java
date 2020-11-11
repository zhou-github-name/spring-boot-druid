package com.springboot.datasource.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.util.JdbcConstants;

@Component
@ConfigurationProperties(prefix = "spring.datasource.druid.sqlserver")
public class SqlServerProperties extends DSConstant {
	
	public void config(DruidDataSource dataSource) {
    	dataSource.setDbType(JdbcConstants.SQL_SERVER_DRIVER_SQLJDBC4);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setDriverClassName(driverClassName);
    }

	@Override
	public String toString() {
		return "SqlServerProperties [url=" + url + ", username=" + username + ", password=" + password
				+ ", driverClassName=" + driverClassName + "]";
	}
	
}
