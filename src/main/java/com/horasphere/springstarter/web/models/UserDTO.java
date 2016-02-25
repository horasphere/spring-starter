package com.horasphere.springstarter.web.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
public class UserDTO
{
    @Id
    private String id;
    private String email;
    private String firstName;
    private String lastName;

    @CollectionTable(name="user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name="role")
    List<String> roles;

    public UserDTO()
    {
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public List<String> getRoles()
    {
        return roles;
    }

    public void setRoles(List<String> roles)
    {
        this.roles = roles;
    }
}
