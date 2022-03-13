package com.company.springboot.service;

import com.company.springboot.model.Task;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();

    void saveTask(Task taskDto);

    Task getTaskById(long id);

    void deleteTaskById(long id);

    void markTaskAsDone(long id);

    Page<Task> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
