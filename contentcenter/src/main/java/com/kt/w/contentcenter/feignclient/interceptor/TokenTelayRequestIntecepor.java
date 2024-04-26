package com.kt.w.contentcenter.feignclient.interceptor;

//省略包名
import feign.RequestInterceptor;
import feign.RequestTemplate;
import com.alibaba.cloud.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @version v0.0.1
 * @apiNote feign传递token拦截统一处理 使用RequestInterceptor拦截器 配置的方式传递
 */
public class TokenTelayRequestIntecepor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        // 从header获取X-token
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes srat = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = srat.getRequest();
        String token = request.getHeader("X-Token");
        if (StringUtils.isNotBlank(token)) {
            //将token传递出去
            requestTemplate.header("X-Token", token);
        }
    }
}
