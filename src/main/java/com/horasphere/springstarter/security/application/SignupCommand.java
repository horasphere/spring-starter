package com.horasphere.springstarter.security.application;

import java.util.List;

public class SignupCommand
{
    String email;
    String clearPassword;
    String firsName;
    String lastName;
    List<String> roles;

    public SignupCommand(String email, String clearPassword, String firsName, String lastName,
                         List<String> roles)
    {
        this.email = email;
        this.clearPassword = clearPassword;
        this.firsName = firsName;
        this.lastName = lastName;
        this.roles = roles;
    }

    public String getEmail()
    {
        return email;
    }

    public String getClearPassword()
    {
        return clearPassword;
    }

    public String getFirsName()
    {
        return firsName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public List<String> getRoles()
    {
        return roles;
    }
}
