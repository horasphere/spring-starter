package com.horasphere.springstarter.security.infrastructure.repository.mybatis.dbo;

public class DBUserRole
{
    private String email;
    private String role;

    public DBUserRole()
    {

    }

    public DBUserRole(String email, String role)
    {
        this.email = email;
        this.role = role;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }
}
