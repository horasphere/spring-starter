package com.horasphere.springstarter.security.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User implements UserDetails
{
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private List<String> roles;
    private boolean enabled;

    public User(String email, String password, String firstName, String lastName, List<String> roles, boolean enabled)
    {
        this.email = email;
        this.firstName = firstName;
        this.password = password;
        this.lastName = lastName;
        this.roles = roles;
        this.enabled = enabled;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public List<GrantedAuthority> getAuthorities()
    {
        List<GrantedAuthority> result = new ArrayList<GrantedAuthority>();

        for(String role: roles)
            result.add(new SimpleGrantedAuthority(role));

        return Collections.unmodifiableList(result);
    }

    public List<String> getRoles()
    {
        return Collections.unmodifiableList(roles);
    }

    public String getUsername()
    {
        return email;
    }

    public boolean isAccountNonExpired()
    {
        return true;
    }

    public boolean isAccountNonLocked()
    {
        return true;
    }

    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    public boolean isEnabled()
    {
        return enabled;
    }
}
