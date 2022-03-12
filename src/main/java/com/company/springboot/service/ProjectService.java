package com.company.springboot.service;

import com.company.springboot.model.Project;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ProjectService {
    List<Project> getAllProjects();

    List<Project> getAllProjectsWithoutTeam();

    void saveProject(Project project);

    Project getProjectById(long id);

    void deleteProjectById(long id);

    Page<Project> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
