package com.horasphere.shared.ddd;

public abstract class Entity extends IdentifiedDomainObject
{
    public Entity()
    {
        super();
    }

    public Entity(String id)
    {
        super(id);
    }
}
