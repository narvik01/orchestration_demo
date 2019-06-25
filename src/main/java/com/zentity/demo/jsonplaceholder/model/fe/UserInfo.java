package com.zentity.demo.jsonplaceholder.model.fe;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class UserInfo {

    @NotNull
    private Integer userId;
    @NotNull
    private String userName;
    private String name;
    @Email
    private String email;
    private List<Todo> todos;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Todo> getTodos() {
        if (todos == null)
            todos = new ArrayList<>();
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }
}
