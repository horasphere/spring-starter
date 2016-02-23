package com.horasphere.springstarter.security.domain;

import java.util.List;

public interface UserRepository
{
    int create(User user);
    List<User> findAll();
    User findByEmail(String email);
}
