package com.horasphere.springstarter.security.domain;

public class PasswordStrengthException extends Exception
{
    public PasswordStrengthException(String message)
    {
        super(message);
    }
}
