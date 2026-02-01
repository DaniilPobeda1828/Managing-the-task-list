package com.task_manager.app.repository;

import com.task_manager.app.entity.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TaskRepository {

    private static final List<Task> tasks = new ArrayList<>();

    public TaskRepository() {
        tasks.add(new Task("Помыть посуду", "Помыть всю посуду на кухне"));
        tasks.add(new Task("Сделать ДЗ", "Выполнить практическое задание"));
    }

    public List<Task> findAll() {
        return new ArrayList<>(tasks);
    }

    public Optional<Task> findById(UUID id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst();
    }

    public Task save(Task task) {
        if (task.getId() == null) {
            task.setId(UUID.randomUUID());
            tasks.add(task);
        } else {
            deleteById(task.getId());
            tasks.add(task);
        }
        return task;
    }

    public boolean deleteById(UUID id) {
        return tasks.removeIf(task -> task.getId().equals(id));
    }
}