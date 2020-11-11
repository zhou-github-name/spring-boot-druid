package com.springboot.datasource.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.util.JdbcConstants;

/**
 * 多数据源的配置
 *
 */
@Component
@ConfigurationProperties(prefix = "spring.datasource.druid.scott")
public class ScottProperties extends DSConstant {
	
	public void config(DruidDataSource dataSource) {
		dataSource.setDbType(JdbcConstants.MYSQL);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setDriverClassName(driverClassName);
    }

	@Override
	public String toString() {
		return "ScottProperties [url=" + url + ", username=" + username + ", password=" + password
				+ ", driverClassName=" + driverClassName + "]";
	}

}
