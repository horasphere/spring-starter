package com.horasphere.shared.ddd;

import java.util.UUID;

public class UUIDSequenceGenerator implements SequenceGenerator
{
    @Override
    public String generateNextValue()
    {
        return UUID.randomUUID().toString();
    }
}
