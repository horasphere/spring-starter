package com.horasphere.shared.ddd;

public abstract class IdentifiedDomainObject implements Identity
{
    String id;

    public IdentifiedDomainObject()
    {
        this.setId(null);
    }

    public IdentifiedDomainObject(String id)
    {
        this.setId(id);
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String id()
    {
        return id;
    }

    public boolean isPersisted() {
        return (this.id != null);
    }
}
