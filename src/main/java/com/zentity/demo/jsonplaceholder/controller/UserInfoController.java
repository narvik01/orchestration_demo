package com.zentity.demo.jsonplaceholder.controller;

import com.zentity.demo.jsonplaceholder.model.fe.UserInfo;
import com.zentity.demo.jsonplaceholder.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

@RestController
public class UserInfoController {

    @Autowired
    private UserInfoService backendService;

    @GetMapping("/userinfo/${userId}")
    public UserInfo getUserInfo(@PathVariable @Min(0) Integer userId) {
        return backendService.getUserInfo(userId);
    }
}
