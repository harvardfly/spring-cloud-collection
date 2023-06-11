package com.kingsoft.woa.contentcenter.sentinel;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

/**
 * @author zpf
 */
@Component
public class MyRequestOriginParser implements RequestOriginParser {

    @Override
    public String parseOrigin(HttpServletRequest request) {
        // 从请求Header中获取名为 origin的参数并返回
        // 如果获取不到origin参数，那么就抛异常
        String origin = request.getHeader("origin");
        // 到sentinel控制台可以设置 授权规则 来源origin为xxx则拒绝访问
        if (StringUtils.isBlank(origin)) {
            throw new IllegalArgumentException("origin must be specified");
        }
        return origin;
    }
}