package com.in28minutes.rest.webservices.restfulwebservices.model;

import java.util.Date;

public class User {

    private Integer id;
    private String name;
    private Date birthDate;

    protected User() {
    }

    public User(Integer id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }
}
