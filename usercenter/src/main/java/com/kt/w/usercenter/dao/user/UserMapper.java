package com.kt.w.usercenter.dao.user;

import com.kt.w.usercenter.domain.entity.user.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserMapper extends Mapper<User> {
}

