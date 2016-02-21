package com.horasphere.springstarter.domain;

import java.util.List;

public interface UserRepository
{
    void create(User user);
    List<User> findAll();
    User findByEmail(String email);
}
