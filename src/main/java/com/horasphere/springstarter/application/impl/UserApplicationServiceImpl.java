package com.horasphere.springstarter.application.impl;

import com.horasphere.springstarter.application.UserApplicationService;
import com.horasphere.springstarter.domain.InvalidPasswordException;
import com.horasphere.springstarter.domain.PasswordStrengthPolicy;
import com.horasphere.springstarter.domain.User;
import com.horasphere.springstarter.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class UserApplicationServiceImpl implements UserApplicationService
{
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    PasswordStrengthPolicy passwordStrengthPolicy;

    public UserApplicationServiceImpl(PasswordStrengthPolicy passwordStrengthPolicy)
    {
        this.passwordStrengthPolicy = passwordStrengthPolicy;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userRepository.findByEmail(username);

        if(user == null) {
            throw new UsernameNotFoundException("Email not found...");

        }
        return user;
    }

    @Transactional
    public void signUp(String email, String clearPassword, String firsName, String lastName, List<String> roles) throws InvalidPasswordException
    {
        String error = passwordStrengthPolicy.validate(clearPassword);

        if(error != null) {
            throw new InvalidPasswordException(error);
        }

        String cryptedPassword = passwordEncoder.encode(clearPassword);

        User user = new User(email, cryptedPassword, firsName, lastName, roles, true);

        userRepository.create(user);
    }
}
