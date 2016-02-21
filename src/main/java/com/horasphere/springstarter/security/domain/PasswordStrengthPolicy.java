package com.horasphere.springstarter.security.domain;

public interface PasswordStrengthPolicy
{
    String validate(String password);
}
