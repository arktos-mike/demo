package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;

@RestController
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/tasks")
    public Task create(@RequestBody Task task) {
        return taskRepository.save(task);
    }
    
    @GetMapping("/tasks")
    public Iterable<Task> list() {
        return taskRepository.findAll();
    }

    @GetMapping("/tasks/{id}")
    public Task getById(@PathVariable Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @PutMapping("/tasks/{id}")
    public Task editById(@PathVariable Long id, @RequestBody Task task) {
        task.id=id;
        return taskRepository.save(task);
    }
    
    @DeleteMapping("/tasks/{id}")
    public void delById(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }

    @PatchMapping("/tasks/{id}")
    public void doneById(@PathVariable Long id, @RequestBody Task task) {
        if (task.done) {
            taskRepository.markAsDone(id);
        }
    }

    @PatchMapping("/tasks/{id}:mark-as-done")
    public void doneById2(@PathVariable Long id) {
            taskRepository.markAsDone(id);
    }
}
