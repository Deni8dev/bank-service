package com.slmndr.services;

import com.slmndr.entities.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User find(String username);

    Boolean save(User user);

}
