package com.springboot.datasource.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.util.JdbcConstants;

@Component
@ConfigurationProperties(prefix = "spring.datasource.druid.db2")
public class DBToProperties extends DSConstant {
	
	public void config(DruidDataSource dataSource) {
    	dataSource.setDbType(JdbcConstants.DB2);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setDriverClassName(driverClassName);
    }

	@Override
	public String toString() {
		return "DBToProperties [url=" + url + ", username=" + username + ", password=" + password + ", driverClassName="
				+ driverClassName + "]";
	}

}
