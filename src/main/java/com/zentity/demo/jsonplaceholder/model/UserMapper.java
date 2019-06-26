package com.zentity.demo.jsonplaceholder.model;

import com.zentity.demo.jsonplaceholder.model.be.Todo;
import com.zentity.demo.jsonplaceholder.model.be.User;
import com.zentity.demo.jsonplaceholder.model.fe.FETodo;
import com.zentity.demo.jsonplaceholder.model.fe.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    @Mapping(target = "todos", ignore = true)
    UserInfo mapUserToUserInfo(User user);
    FETodo mapTodo(Todo todo);
}
