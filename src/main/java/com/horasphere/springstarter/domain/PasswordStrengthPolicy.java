package com.horasphere.springstarter.domain;

public interface PasswordStrengthPolicy
{
    String validate(String password);
}
