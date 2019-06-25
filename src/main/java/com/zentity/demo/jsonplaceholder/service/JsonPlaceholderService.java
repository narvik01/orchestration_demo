package com.zentity.demo.jsonplaceholder.service;

import com.zentity.demo.jsonplaceholder.model.fe.UserInfo;
import org.springframework.stereotype.Service;

@Service
public class JsonPlaceholderService implements UserInfoService {

    @Override
    public UserInfo getUserInfo(int userId) {
        return null;
    }

}
