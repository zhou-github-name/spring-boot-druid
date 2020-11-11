package com.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.springboot.interceptor.UserTokenInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Bean
	public UserTokenInterceptor userTokenInterceptor() {
		return new UserTokenInterceptor();
	}

	/**
	 * 注册拦截器
	 * 
	 * @param registry
	 * @param.重写addInterceptors 添加监听的路径 
	 * @paramaddPathPatterns 添加监听的路径地址
	 * @paramexcludePathPatterns 排除一些路径
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(userTokenInterceptor())
				.addPathPatterns("/hello")
				.addPathPatterns("/shopcart/add")
				.addPathPatterns("/shopcart/del")
				.excludePathPatterns("/myorders/deliver")
				.excludePathPatterns("/orders/notifyMerchantOrderPaid");
		WebMvcConfigurer.super.addInterceptors(registry);
	}

}
