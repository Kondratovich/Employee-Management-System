package com.company.springboot.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

public class TeamDto {

    private long id;

    @Size(min = 2, max = 300)
    private String officeLocation;

    @NotEmpty
    private List<Long> projectsIds;

    @NotEmpty
    private List<Long> usersIds;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOfficeLocation() {
        return officeLocation;
    }

    public void setOfficeLocation(String officeLocation) {
        this.officeLocation = officeLocation;
    }

    public List<Long> getProjectsIds() {
        return projectsIds;
    }

    public void setProjectsIds(List<Long> projectsIds) {
        this.projectsIds = projectsIds;
    }

    public List<Long> getUsersIds() {
        return usersIds;
    }

    public void setUsersIds(List<Long> usersIds) {
        this.usersIds = usersIds;
    }
}
