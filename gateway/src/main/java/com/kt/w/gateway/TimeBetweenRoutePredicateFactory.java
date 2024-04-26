package com.kt.w.gateway;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/*
 *创建自定义工程类：实现自定义路由谓词工厂
*/
@Component
public class TimeBetweenRoutePredicateFactory
        extends AbstractRoutePredicateFactory<TimeBeweenConfig> {
    public TimeBetweenRoutePredicateFactory() {
        super(TimeBeweenConfig.class);
    }

    /**
     * 路由谓词工厂的核心方法
     * @param config
     * @return
     */
    @Override
    public Predicate<ServerWebExchange> apply(TimeBeweenConfig config) {
        LocalTime start = config.getStart();
        LocalTime end = config.getEnd();
        return exchange -> {
            LocalTime now = LocalTime.now();
            return now.isAfter(start) && now.isBefore(end);
        };
    }

    /**
     * 控制配置类TimeBeweenConfig和配置文件application#predicates的映射关系
     * @return
     */
    @Override
    public List<String> shortcutFieldOrder() {
        //TimeBeweenConfig类的两个属性start和end分别对应application中TimeBetween的9:00和17:00两个参数
        return Arrays.asList("start", "end");
    }

    //测试输出SpringCloudGateway的时间格式
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
        System.out.println(formatter.format(LocalTime.now()));
    }
}
