package com.company.demo.common.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * This is main configuration of DemoApplication
 *
 * @author mina.mn@gmail.com
 */
@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.company.demo.data.repository")
@ComponentScan(basePackages = "com.company.demo")
public class AppConfig {

}
