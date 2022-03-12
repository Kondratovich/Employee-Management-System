package com.company.springboot.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TaskDto {

    private long id;

    @NotNull
    private long projectId;

    @Size(min = 2, max = 30)
    private String name;

    @Size(min = 2, max = 300)
    private String description;

    @NotNull
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
