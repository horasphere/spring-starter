package com.horasphere.springstarter.security.application.impl;

import com.horasphere.springstarter.security.application.SignupCommand;
import com.horasphere.springstarter.security.application.UserApplicationService;
import com.horasphere.springstarter.security.domain.PasswordStrengthException;
import com.horasphere.springstarter.security.domain.PasswordStrengthPolicy;
import com.horasphere.springstarter.security.domain.User;
import com.horasphere.springstarter.security.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

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
    public void signup(SignupCommand signupCommand) throws PasswordStrengthException
    {
        String error = passwordStrengthPolicy.validate(signupCommand.getClearPassword());

        if(error != null) {
            throw new PasswordStrengthException(error);
        }

        String cryptedPassword = passwordEncoder.encode(signupCommand.getClearPassword());

        User user = new User(signupCommand.getEmail(),
            cryptedPassword,
            signupCommand.getFirsName(),
            signupCommand.getLastName(),
            signupCommand.getRoles(),
            true);

        userRepository.create(user);
    }
}
