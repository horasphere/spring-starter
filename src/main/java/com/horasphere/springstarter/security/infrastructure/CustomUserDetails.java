package com.horasphere.springstarter.security.infrastructure;

import com.horasphere.springstarter.security.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CustomUserDetails implements UserDetails
{
    private User user;

    public CustomUserDetails(User user)
    {
        this.user = user;
    }

    @Override
    public String getPassword()
    {
        return user.getPassword();
    }

    @Override
    public boolean isEnabled()
    {
        return user.isEnabled();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        List<GrantedAuthority> result = new ArrayList<GrantedAuthority>();

        for(String role: user.getRoles())
            result.add(new SimpleGrantedAuthority(role));

        return Collections.unmodifiableList(result);
    }

    @Override
    public String getUsername()
    {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return false;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return false;
    }
}
