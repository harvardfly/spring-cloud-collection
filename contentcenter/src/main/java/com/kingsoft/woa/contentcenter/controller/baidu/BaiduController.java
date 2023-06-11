package com.kingsoft.woa.contentcenter.controller.baidu;

import com.kingsoft.woa.contentcenter.feignclient.BaiduFeignClient;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;

@RefreshScope
@RestController
@RequiredArgsConstructor
@RequestMapping("/baidu")
public class BaiduController {
    private final BaiduFeignClient baiduFeignClient;
    @Value("${my.config}")  // 从配置文件中加载
    private String myConfig;

    //使用slf4j的Logger类记录日志，此条日志相关的追踪信息会由Sleuth自动生成并记录。
    private static Logger log= LoggerFactory.getLogger(BaiduController.class);

    @GetMapping("/index")
    public String baiduIndex(@RequestHeader("token") String token) {
        // 从header中获取token
        System.out.println(token);
        System.out.println(myConfig);
        log.info("fist span");
        return this.baiduFeignClient.baiduIndex(token);
    }
}
