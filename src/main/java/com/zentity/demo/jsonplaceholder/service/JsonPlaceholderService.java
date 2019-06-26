package com.zentity.demo.jsonplaceholder.service;

import com.zentity.demo.jsonplaceholder.exception.UserNotFoundException;
import com.zentity.demo.jsonplaceholder.model.UserMapper;
import com.zentity.demo.jsonplaceholder.model.be.Todo;
import com.zentity.demo.jsonplaceholder.model.be.User;
import com.zentity.demo.jsonplaceholder.model.fe.FETodo;
import com.zentity.demo.jsonplaceholder.model.fe.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class JsonPlaceholderService implements UserInfoService {

    private static final Logger logger = LoggerFactory.getLogger(JsonPlaceholderService.class);

    private final WebClient webClient;
    private final UserMapper userMapper;

    @Autowired
    public JsonPlaceholderService(WebClient webClient, UserMapper userMapper) {
        this.webClient = webClient;
        this.userMapper = userMapper;
    }

    @Override
    public UserInfo getUserInfo(int userId) {
        Mono<UserInfo> userInfoMono = getUserInfoMono(userId);
        return userInfoMono.block();
    }

    private Mono<UserInfo> getUserInfoMono(Integer userId) {
        Mono<UserInfo> userMono = webClient.get()
            .uri(uriBuilder -> uriBuilder.path("/users/{userId}").build(userId))
            .retrieve()
            .onStatus(HttpStatus.NOT_FOUND::equals, resp -> Mono.error(new UserNotFoundException(userId)))
            .bodyToMono(User.class)
            .map(userMapper::mapUserToUserInfo);

        Flux<FETodo> todoFlux = webClient.get()
            .uri(uriBuilder -> uriBuilder.path("/todos").queryParam("userId", userId).build())
            .retrieve()
            .bodyToFlux(Todo.class)
            .map(userMapper::mapTodo);

        Mono<List<FETodo>> todoListMono = todoFlux.collectList();

        return userMono.zipWith(todoListMono, ((userInfo, feTodos) -> {
            userInfo.getTodos().addAll(feTodos);
            return userInfo;
        }));

    }

}
