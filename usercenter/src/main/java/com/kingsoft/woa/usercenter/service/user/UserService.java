package com.kingsoft.woa.usercenter.service.user;

import com.kingsoft.woa.usercenter.dao.user.UserMapper;
import com.kingsoft.woa.usercenter.domain.entity.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserMapper userMapper;

    public User findById(Integer id) {
        log.info("UserService enter");
        return this.userMapper.selectByPrimaryKey(id);
    }
}
