package com.horasphere.springstarter.security.infrastructure;

import com.horasphere.springstarter.security.domain.EncryptionService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderEncryptionService implements EncryptionService
{
    PasswordEncoder passwordEncoder;

    public PasswordEncoderEncryptionService(PasswordEncoder passwordEncoder)
    {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encrypt(String value)
    {
        return passwordEncoder.encode(value);
    }
}
