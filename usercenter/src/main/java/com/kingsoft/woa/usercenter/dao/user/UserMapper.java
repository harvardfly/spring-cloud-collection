package com.kingsoft.woa.usercenter.dao.user;

import com.kingsoft.woa.usercenter.domain.entity.user.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserMapper extends Mapper<User> {
}

