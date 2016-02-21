package com.horasphere.springstarter.utils;

import java.util.ArrayList;
import java.util.List;

public abstract class Converter<S, T> implements org.springframework.core.convert.converter.Converter<S, T>
{
    public List<T> convert(List<S> sources) {
        List<T> result = new ArrayList<T>();

        for(S source: sources) {
            result.add(this.convert(source));
        }

        return result;
    }
}
