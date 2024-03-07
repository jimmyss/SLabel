package com.jimmyss.slabel.controller;

import com.jimmyss.slabel.component.BaseResponse;
import com.jimmyss.slabel.service.UserService;
import com.jimmyss.slabel.service.serviceImpl.UserServiceImpl;
import com.jimmyss.slabel.vo.UserBaseVO;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    @Resource
    private UserServiceImpl userService;

    @PostMapping("/login")
    BaseResponse login(@RequestBody @Valid UserBaseVO userBaseVO){
        return userService.loginService(userBaseVO.getUsername(), userBaseVO.getPassword());
    }

    @PostMapping("/register")
    BaseResponse register(@RequestBody @Valid UserBaseVO userBaseVO) {
        return userService.registerService(userBaseVO.getUsername(), userBaseVO.getPassword());
    }
}
