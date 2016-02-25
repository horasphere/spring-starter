package com.horasphere.springstarter.security.application.impl;

import com.horasphere.springstarter.security.application.SignupCommand;
import com.horasphere.springstarter.security.application.UserApplicationService;
import com.horasphere.springstarter.security.domain.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

public class UserApplicationServiceImpl implements UserApplicationService
{
    EncryptionService encryptionService;
    UserRepository userRepository;
    PasswordStrengthPolicy passwordStrengthPolicy;

    public UserApplicationServiceImpl(EncryptionService encryptionService,
                                      UserRepository userRepository,
                                      PasswordStrengthPolicy passwordStrengthPolicy)
    {
        this.encryptionService = encryptionService;
        this.userRepository = userRepository;
        this.passwordStrengthPolicy = passwordStrengthPolicy;
    }

    @Transactional
    public String signup(SignupCommand signupCommand)
    {
        Assert.notNull(signupCommand.getEmail());
        Assert.notNull(signupCommand.getClearPassword());
        Assert.notNull(signupCommand.getFirsName());
        Assert.notNull(signupCommand.getLastName());
        Assert.notNull(signupCommand.getRoles());

        String error = passwordStrengthPolicy.validate(signupCommand.getClearPassword());

        if(error != null) {
            throw new PasswordStrengthException(error);
        }

        String cryptedPassword = encryptionService.encrypt(signupCommand.getClearPassword());

        User user = new User(userRepository.nextId(),
            signupCommand.getEmail(),
            cryptedPassword,
            signupCommand.getFirsName(),
            signupCommand.getLastName(),
            signupCommand.getRoles(),
            true);

        userRepository.create(user);

        return user.id();
    }
}
