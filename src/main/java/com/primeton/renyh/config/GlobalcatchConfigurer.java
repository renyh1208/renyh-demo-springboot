package com.primeton.renyh.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class GlobalcatchConfigurer extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// addPathPatterns 用于添加拦截规则
		// excludePathPatterns 配置某些路径不拦截
		/*
		 * InterceptorRegistration addInterceptor =
		 * registry.addInterceptor((HandlerInterceptor) new
		 * GlobalExceptionHandler());
		 * addInterceptor.excludePathPatterns("/test");
		 * addInterceptor.addPathPatterns("/**");
		 */
		// 如果有多个拦截器
		// InterceptorRegistration addInterceptor2 = registry.addInterceptor(new
		// MyInterceptor2());
		super.addInterceptors(registry);

	}

}
