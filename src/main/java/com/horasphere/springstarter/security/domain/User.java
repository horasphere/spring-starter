package com.horasphere.springstarter.security.domain;

import com.horasphere.shared.EmailValidator;
import com.horasphere.shared.CustomAssert;
import com.horasphere.shared.ddd.Entity;


import java.util.*;

public class User extends Entity
{
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Set<String> roles;
    private boolean enabled;

    public User(String id, String email, String password, String firstName, String lastName,
                List<String> roles, boolean enabled)
    {
        super(id);

        this.setEmail(email);

        CustomAssert.assertNotNullOrEmpty(password, "password");
        CustomAssert.assertNotNullOrEmpty(firstName, "firstName");
        CustomAssert.assertNotNullOrEmpty(lastName, "lastName");

        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;

        this.roles = (roles == null) ? new HashSet<String>() : new HashSet<String>(roles);
        this.enabled = enabled;
    }

    private void setEmail(String email) {
        CustomAssert.assertNotNullOrEmpty(email, "Email must be defined.");

        if(!new EmailValidator().validate(email)) {
            throw new IllegalArgumentException("Invalid email: " + email);
        }

        this.email = email;
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

    public List<String> getRoles()
    {
        return new ArrayList<String>(roles);
    }

    public void addRole(String role) {
        this.roles.add(role);
    }

    public void removeRole(String role) {
        this.roles.remove(role);
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public void disable() {
        this.enabled = false;
    }

    public void enable() {
        this.enabled = true;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return email.equals(user.email);

    }

    @Override
    public int hashCode()
    {
        return email.hashCode();
    }
}
