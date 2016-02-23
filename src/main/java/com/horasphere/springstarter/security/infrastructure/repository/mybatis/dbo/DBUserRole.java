package com.horasphere.springstarter.security.infrastructure.repository.mybatis.dbo;

public class DBUserRole
{
    private int id;
    private String role;

    public DBUserRole()
    {

    }

    public DBUserRole(int id, String role)
    {
        this.id = id;
        this.role = role;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
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
