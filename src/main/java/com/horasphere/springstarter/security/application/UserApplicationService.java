package com.horasphere.springstarter.security.application;

import com.horasphere.springstarter.security.domain.InvalidPasswordException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserApplicationService extends UserDetailsService
{
    void signUp(String email, String clearPassword, String firsName, String lastName, List<String> roles) throws InvalidPasswordException;
}