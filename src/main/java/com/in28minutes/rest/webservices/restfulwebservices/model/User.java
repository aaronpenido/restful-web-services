package com.in28minutes.rest.webservices.restfulwebservices.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@ApiModel(description = "Description about the user")
public class User {

    private static final String NAME_MESSAGE = "Name should have at least 2 characters";
    private static final String BIRTH_MESSAGE = "Birth Date should be in a past time";

    private Integer id;
    @Size(min = 2, message = NAME_MESSAGE)
    @ApiModelProperty(notes = NAME_MESSAGE)
    private String name;
    @Past(message = BIRTH_MESSAGE)
    @ApiModelProperty(notes = BIRTH_MESSAGE)
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
