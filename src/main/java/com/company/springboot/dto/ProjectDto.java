package com.company.springboot.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class ProjectDto {

    private long id;

    @NotNull
    private long customerId;

    private long teamId;

    @Size(min = 2, max = 30)
    private String name;

    @Size(min = 2, max = 300)
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date dateAdded;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date deadline;

    @Min(100)
    @Max(999999)
    private long price;

    public ProjectDto() {
    }

    public ProjectDto(long id, long customerId, String name, String description, Date dateAdded, Date deadline, long price) {
        this.id = id;
        this.customerId = customerId;
        this.name = name;
        this.description = description;
        this.dateAdded = dateAdded;
        this.deadline = deadline;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
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

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
