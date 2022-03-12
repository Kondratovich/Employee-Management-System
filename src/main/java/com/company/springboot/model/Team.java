package com.company.springboot.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

    @Column(name = "office_location")
    private String officeLocation;

    @OneToMany(mappedBy = "team")
    private Collection<Project> projects;

    @OneToMany(mappedBy = "userTeam")
    private Collection<User> users;

    public Team() {
    }

    public Team(String officeLocation) {
        this.officeLocation = officeLocation;
    }

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

    public Collection<Project> getProjects() {
        return projects;
    }

    public void setProjects(Collection<Project> projects) {
        this.projects = projects;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}
