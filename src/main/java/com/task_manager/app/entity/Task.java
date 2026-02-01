package com.task_manager.app.entity;

import java.util.UUID;

public class Task {
    private UUID id;
    private String title;
    private String description;
    private boolean completed;

    public Task() {
    }

    public Task(String title, String description) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.completed = false;
    }

    // Геттеры и сеттеры (как в предыдущем ответе)
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}