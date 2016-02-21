package com.horasphere.springstarter.domain;

public class InvalidPasswordException extends Exception
{
    public InvalidPasswordException(String message)
    {
        super(message);
    }
}
