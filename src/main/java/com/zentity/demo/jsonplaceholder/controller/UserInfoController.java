package com.zentity.demo.jsonplaceholder.controller;

import com.zentity.demo.jsonplaceholder.model.fe.UserInfo;
import com.zentity.demo.jsonplaceholder.service.UserInfoService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

@RestController("/userinfo")
@Validated
public class UserInfoController {

    private final UserInfoService backendService;

    @Autowired
    public UserInfoController(UserInfoService backendService) {
        this.backendService = backendService;
    }

    @ApiOperation(value="Operation to get basic user information with todo list.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "OK", responseHeaders = {
            @ResponseHeader(name = "ETag", description = "ETag of the data.")
        }),
        @ApiResponse(code = 304, message = "Not modified. If current ETag matches If-None-Match header."),
        @ApiResponse(code = 400, message = "Bad request. Incorrect userId parameter."),
        @ApiResponse(code = 404, message = "Bad request. User id not found."),
        @ApiResponse(code = 504, message = "Gateway timeout. No response received from backend services in time.")
    })
    @GetMapping("/user/{userId}")
    public UserInfo getUserInfo(
        @ApiParam(value = "user identification", required = true, defaultValue = "1")
        @PathVariable(name = "userId")
        @Min(0)
        Integer userId) {

        return backendService.getUserInfo(userId);
    }

}
