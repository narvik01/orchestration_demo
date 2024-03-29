package com.zentity.demo.jsonplaceholder.model.fe;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class UserInfo {

    @NotNull
    private Integer id;
    @NotNull
    private String username;
    private String name;
    @Email
    private String email;
    private List<FETodo> todos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public List<FETodo> getTodos() {
        if (todos == null)
            todos = new ArrayList<>();
        return todos;
    }

    public void setTodos(List<FETodo> todos) {
        this.todos = todos;
    }
}
