package com.horasphere.springstarter.security.domain;

public class InvalidPasswordException extends Exception
{
    public InvalidPasswordException(String message)
    {
        super(message);
    }
}