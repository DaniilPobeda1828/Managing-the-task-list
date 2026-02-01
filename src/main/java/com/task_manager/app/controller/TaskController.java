package com.task_manager.app.controller;

import com.task_manager.app.entity.Task;
import com.task_manager.app.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable UUID id) {
        Optional<Task> task = taskService.getTaskById(id);
        return task.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        task.setId(null);
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable UUID id, @RequestBody Task taskDetails) {
        Optional<Task> updatedTask = taskService.updateTask(id, taskDetails);
        return updatedTask.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable UUID id) {
        boolean deleted = taskService.deleteTask(id);
        if (deleted) {
            return ResponseEntity.ok("Task with id: " + id + " has been deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Task with id: " + id + " not found");
    }
}
