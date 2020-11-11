package com.springboot.datasource.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.util.JdbcConstants;

/**
 * <p>
 * 数据库数据源配置
 * </p>
 * <p>
 * 说明:这个类中包含了许多默认配置,若这些配置符合您的情况,您可以不用管,若不符合,建议不要修改本类,建议直接在"application.yml"中配置即可
 * </p>
 *
 */
@Component
@ConfigurationProperties(prefix = "spring.datasource.druid.core")
public class CoreProperties extends DSConstant {
	
	public void config(DruidDataSource dataSource) {
    	dataSource.setDbType(JdbcConstants.MYSQL);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setDriverClassName(driverClassName);
    }

	@Override
	public String toString() {
		return "CoreProperties [url=" + url + ", username=" + username + ", password=" + password + ", driverClassName="
				+ driverClassName + "]";
	}
	
}
