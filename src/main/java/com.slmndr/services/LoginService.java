package com.slmndr.services;

import com.slmndr.entities.User;

public interface LoginService {

    User login(String username, String password);
}
