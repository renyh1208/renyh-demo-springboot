package com.primeton.renyh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * 应用启动类
 */
@EnableSwagger2
@SpringBootApplication
@ComponentScan(basePackages = { "com.primeton.renyh" })
public class Application {

	public static void main(String[] args) {
		// springboot 程序的入口
		SpringApplication.run(Application.class, args);
	}


}
