package com.horasphere.springstarter.security.domain;

import java.util.List;

public interface UserRepository
{
    String nextId();
    void create(User user);
    User findById(String id);
    List<User> findAll();
    User findByEmail(String email);
}
