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

    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    private Collection<Project> projects;

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
}
