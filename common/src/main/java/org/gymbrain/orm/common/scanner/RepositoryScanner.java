package org.gymbrain.orm.common.scanner;

import org.gymbrain.orm.common.repository.BaseRepository;
import org.reflections.Reflections;

import java.lang.reflect.ParameterizedType;
import java.util.Set;

public class RepositoryScanner {
    public static  <R extends BaseRepository> R getImpl(Class<R> type) throws InstantiationException, IllegalAccessException {
        Reflections reflections = new Reflections();
        Set<Class<? extends R>> subTypesOf = reflections.getSubTypesOf(type);
        for (Class<? extends BaseRepository> aClass : subTypesOf) {
            System.out.println(aClass);
        }
        return (R) subTypesOf.stream().findFirst().get().newInstance();
    }
}
