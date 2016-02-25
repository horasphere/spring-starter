package com.horasphere.springstarter.security.infrastructure.repository.mybatis.dbo;

public class DBUserRole
{
    private String userId;
    private String role;

    public DBUserRole()
    {
        super();
    }

    public DBUserRole(String userId, String role)
    {
        this.userId = userId;
        this.role = role;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
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
