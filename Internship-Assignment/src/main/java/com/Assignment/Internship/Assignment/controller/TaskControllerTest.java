// TaskControllerTest.java
package com.Assignment.Internship.Assignment.controller;

import com.Assignment.Internship.Assignment.entity.Task;
import com.Assignment.Internship.Assignment.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddTask() {
        Task task = new Task();
        when(taskService.createTask(task)).thenReturn(task);

        ResponseEntity<Task> responseEntity = taskController.addTask(task);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(task, responseEntity.getBody());
    }

    @Test
    void testGetAllTasks() {
        Page<Task> taskPage = mock(Page.class);
        when(taskService.getAllTasksByUserId(anyLong(), any())).thenReturn(taskPage);

        ResponseEntity<Page<Task>> responseEntity = taskController.getAllTasks(0, 10, "title", "completed");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(taskPage, responseEntity.getBody());
    }

    // Write tests for other controller methods
}
