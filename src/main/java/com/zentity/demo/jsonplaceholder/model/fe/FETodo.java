package com.zentity.demo.jsonplaceholder.model.fe;

public class FETodo {

    private String title;
    private boolean completed;

    public FETodo() {}

    public FETodo(String title, boolean completed) {
        this.title = title;
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
