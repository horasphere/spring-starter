package com.horasphere.springstarter.security.domain;

public class PasswordStrengthException extends RuntimeException
{
    public PasswordStrengthException(String message)
    {
        super(message);
    }
}
