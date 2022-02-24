package com.cn.emio.sl.lblue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

@MapperScan("com.cn.emio.sl.lblue.test.dao")
@SpringBootApplication
@ServletComponentScan
@EnableAsync
@EnableRetry
/**
 * @author Silence_Lurker
 */
public class LblueApplication {

	public static void main(String[] args) {
		SpringApplication.run(LblueApplication.class, args);
	}

}