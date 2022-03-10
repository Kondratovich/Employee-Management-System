package com.company.springboot.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "positions")
public class Position {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

    @Column(name = "position_name")
    private String positionName;

    @Column(name = "vacation_days")
    private int vacationDays;

    @Column(name = "premium_size")
    private int premiumSize;

    @OneToMany(mappedBy = "position")
    private Collection<User> users;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public int getVacationDays() {
        return vacationDays;
    }

    public void setVacationDays(int vacationDays) {
        this.vacationDays = vacationDays;
    }

    public int getPremiumSize() {
        return premiumSize;
    }

    public void setPremiumSize(int premiumSize) {
        this.premiumSize = premiumSize;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}
