package com.kingsoft.woa.contentcenter.configuration;

import org.springframework.context.annotation.Bean;

import feign.Logger;

/**
 * @author zpf
 */
// feign的配置类这里不能增加@Configuration，如果添加了就会变为全局配置文件。
public class UserCenterFeignConfiguration {
    @Bean
    public Logger.Level level() {
        // feign Logger 打印所有信息
        return Logger.Level.FULL;
    }
}
