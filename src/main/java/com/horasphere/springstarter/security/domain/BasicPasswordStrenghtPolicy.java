package com.horasphere.springstarter.security.domain;

public class BasicPasswordStrenghtPolicy implements PasswordStrengthPolicy
{
    int minLength;

    public BasicPasswordStrenghtPolicy(int minLength)
    {
        this.minLength = minLength;
    }

    @Override
    public String validate(String password)
    {
        if(password.length() < minLength)
            return  String.format("Password should have at least %s characters", new Integer(minLength).toString());

        return null;
    }
}
