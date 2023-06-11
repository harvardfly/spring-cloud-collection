package com.kingsoft.woa.contentcenter.feignclient;

import com.kingsoft.woa.contentcenter.configuration.UserCenterFeignConfiguration;
import com.kingsoft.woa.contentcenter.domain.entity.content.dto.user.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "user-center", configuration = UserCenterFeignConfiguration.class)
@FeignClient(name = "user-center")
public interface UserCenterFeignClient {
    @GetMapping("/users/{id}")
    UserDto findById(@PathVariable Integer id);
}
