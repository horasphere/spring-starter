package com.horasphere.springstarter.security.application;

import com.horasphere.springstarter.security.domain.PasswordStrengthException;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserApplicationService extends UserDetailsService
{
    void signup(SignupCommand signupCommand) throws PasswordStrengthException;
}