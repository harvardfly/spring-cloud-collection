package com.kt.w.contentcenter.service.content;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.kt.w.contentcenter.dao.content.ShareMapper;
import com.kt.w.contentcenter.domain.entity.content.Share;
import com.kt.w.contentcenter.domain.entity.content.dto.content.ShareDto;
import com.kt.w.contentcenter.domain.entity.content.dto.user.UserDto;
import com.kt.w.contentcenter.feignclient.UserCenterFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * @author zpf
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ShareService {
    private final ShareMapper shareMapper;
    private final RestTemplate restTemplate;
//    private final DiscoveryClient discoveryClient;
    private final UserCenterFeignClient userCenterFeignClient;

    public ShareDto findById(Integer id) {
        Share share = shareMapper.selectByPrimaryKey(id);
        Integer userId = share.getUserId();
        // 查询user-center服务所有实例信息
//        List<ServiceInstance> instances = discoveryClient.getInstances("user-center");
//        List<String> targetURLS = instances.stream()
//                .map(instance -> instance.getUri().toString() + "/users/{id}")
//                .toList();
//        int i = ThreadLocalRandom.current().nextInt(targetURLS.size());
//        log.info("targetURL：{}", targetURLS.get(i));

        // restTemplate会通过@LoadBalanced注解自动执行负载均衡 获取user-center对应的地址
//        ResponseEntity<UserDto> entity = this.restTemplate
//                .getForEntity("http://user-center/users/{userId}", UserDto.class, userId);

        //RestTemplate传递Token 使用exchange传递token
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("X-Token", "");
//        ResponseEntity<UserDto> exchange = restTemplate.exchange(
//                "http://user-center/users/{userId}",
//                HttpMethod.GET,
//                new HttpEntity<>(headers),
//                UserDto.class,
//                userId
//        );

        UserDto userDto = this.userCenterFeignClient.findById(userId);

        ShareDto shareDto = new ShareDto();
        BeanUtils.copyProperties(share, shareDto);
        shareDto.setWxNickname(userDto.getWxNickname());
        return shareDto;
    }
}
