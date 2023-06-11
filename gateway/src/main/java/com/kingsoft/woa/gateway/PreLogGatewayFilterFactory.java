package com.kingsoft.woa.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

// 创建自定义过滤器工厂类PreLogGatewayFilterFactoryGatewayFilterFactory 务必注意，自定义的过滤器工厂类必须以GatewayFilterFactory结尾
// 这里PreLogGatewayFilterFactory就映射到配置文件的PreLog

@Slf4j
@Component
public class PreLogGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {
    @Override
    public GatewayFilter apply(NameValueConfig config) {
        return ((exchange, chain) -> {
            //获取application中配置的两个参数的值，config.getName()=a；config.getValue()=b
            log.info("请求进来了...{},{}", config.getName(), config.getValue());
            ServerHttpRequest modifiedRequest = exchange.getRequest()
                    .mutate()
                    .build();
            ServerWebExchange modifiedExchange = exchange.mutate()
                    .request(modifiedRequest)
                    .build();

            return chain.filter(modifiedExchange);
        });
    }
}
