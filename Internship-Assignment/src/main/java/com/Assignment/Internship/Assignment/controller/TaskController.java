package com.Assignment.Internship.Assignment.controller;

import com.Assignment.Internship.Assignment.controller.AuthenticationFacade;
import com.Assignment.Internship.Assignment.entity.Task;
import com.Assignment.Internship.Assignment.service.TaskService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @PostMapping
    @ApiOperation(value = "Add a new task")
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        // Validate the task object
        if (task == null) {
            return ResponseEntity.badRequest().build(); // Invalid task data
        }
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @GetMapping
    @ApiOperation(value = "Get all tasks for the current user")
    public ResponseEntity<Page<Task>> getAllTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String filter) {
        Long userId = authenticationFacade.getUserId();
        Sort sort = sortBy != null ? Sort.by(sortBy) : Sort.unsorted();
        Page<Task> taskPage = taskService.getAllTasksByUserId(userId, PageRequest.of(page, size, sort));
        return ResponseEntity.ok(taskPage);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update task details")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        // Validate the task object
        if (task == null) {
            return ResponseEntity.badRequest().build(); // Invalid task data
        }
        Task updatedTask = taskService.updateTask(id, task);
        if (updatedTask != null) {
            return ResponseEntity.ok(updatedTask);
        } else {
            return ResponseEntity.notFound().build(); // Task not found
        }
    }

    @PatchMapping("/{id}/complete")
    @ApiOperation(value = "Mark a task as completed")
    public ResponseEntity<Task> markTaskAsCompleted(@PathVariable Long id) {
        Task completedTask = taskService.markTaskAsCompleted(id);
        if (completedTask != null) {
            return ResponseEntity.ok(completedTask);
        } else {
            return ResponseEntity.notFound().build(); // Task not found
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a task")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
