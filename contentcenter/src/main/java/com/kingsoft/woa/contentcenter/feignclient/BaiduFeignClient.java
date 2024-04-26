package com.kt.w.contentcenter.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "baidu-index", url = "http://www.baidu.com")
public interface BaiduFeignClient {
    @GetMapping("")
    String baiduIndex(@RequestHeader("token") String token); // 表示在FeignClient调用baidu-index时把token传过去
}
