package com.nowcoder.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.nowcoder.interceptor.LoginRequestInterceptor;
import com.nowcoder.interceptor.PassportInterceptor;


@Component
public class WendaConfiguration extends WebMvcConfigurerAdapter {
  @Autowired
  private PassportInterceptor passportInterceptor;
  @Autowired
  private LoginRequestInterceptor loginRequestInterceptor;
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(passportInterceptor);
		//addPathPatterns("/user/*");为访问权限要求
		registry.addInterceptor(loginRequestInterceptor).addPathPatterns("/user/*");
		super.addInterceptors(registry);
	}
}
