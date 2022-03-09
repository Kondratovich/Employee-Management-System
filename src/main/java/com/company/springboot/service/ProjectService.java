package com.company.springboot.service;

import com.company.springboot.model.Project;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProjectService {
    List<Project> getAllProjects();

    void saveProject(Project projectDto);

    Project getProjectById(long id);

    void deleteProjectById(long id);

    Page<Project> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
