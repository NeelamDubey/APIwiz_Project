// TaskRepositoryTest.java
package com.Assignment.Internship.Assignment.repository;

import com.Assignment.Internship.Assignment.entity.Task;
import com.Assignment.Internship.Assignment.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TaskRepositoryTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindByUserId() {
        Page<Task> taskPage = mock(Page.class);
        when(taskRepository.findByUserId(1L, Pageable.unpaged())).thenReturn(taskPage);

        Page<Task> result = taskService.getAllTasksByUserId(1L, Pageable.unpaged());

        assertEquals(taskPage, result);
    }

    // Write tests for other repository methods
}
