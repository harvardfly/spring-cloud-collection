package com.kt.w.usercenter.controller.user;

import com.kt.w.usercenter.domain.entity.user.User;
import com.kt.w.usercenter.service.user.UserService;
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
