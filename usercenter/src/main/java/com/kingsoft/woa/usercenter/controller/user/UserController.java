package com.kingsoft.woa.usercenter.controller.user;

import com.kingsoft.woa.usercenter.domain.entity.user.User;
import com.kingsoft.woa.usercenter.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    @GetMapping("/{id}")
    public User FindById(@PathVariable Integer id) {
        return userService.findById(id);
    }
}
