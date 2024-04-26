package com.kt.w.contentcenter;

import com.kt.w.contentcenter.feignclient.UserCenterFeignClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
//import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
//import ribbonconfiguration.RibbonConfiguration;
import tk.mybatis.spring.annotation.MapperScan;

// 扫描mybatis哪些包里面的接口
@MapperScan("com.kt.w.contentcenter.dao.content")
@Configuration
//@RibbonClient(name="user-center",configuration = RibbonConfiguration.class)
@EnableFeignClients
@SpringBootApplication
public class ContentCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentCenterApplication.class, args);
    }
    // 在spring容器中创建一个对象 类型是RestTemplate 名称是restTemplate
    @Bean
    // 注意：需添加pom依赖包 spring-cloud-starter-loadbalancer 否则会不生效
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
