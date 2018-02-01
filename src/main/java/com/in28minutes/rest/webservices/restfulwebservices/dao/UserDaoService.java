package com.in28minutes.rest.webservices.restfulwebservices.dao;

import com.in28minutes.rest.webservices.restfulwebservices.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static int usersCount = 3;

    static {
        users.add(new User(1, "Adam", new Date()));
        users.add(new User(2, "Jane", new Date()));
        users.add(new User(3, "Jack", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        User newUser = user;
        if(user.getId() == null) {
            newUser = new User(++usersCount, user.getName(), user.getBirthDate());
        }
        users.add(newUser);
        return newUser;
    }

    public User findOne(Integer id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
