package com.horasphere.shared;


public class CustomAssert
{
    public static void assertNotNullOrEmpty(String str, String field) {
        if(str == null || str.isEmpty())
            throw new IllegalArgumentException("'" + field + "' should not be null or empty.");
    }
}
