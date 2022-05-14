package org.gymbrain.orm;

import org.gymbrain.orm.common.scanner.RepositoryScanner;
import org.gymbrain.orm.entity.Person;
import org.gymbrain.orm.repository.PersonRepository;

public class Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        PersonRepository impl = RepositoryScanner.getImpl(PersonRepository.class);
        impl.save(new Person());

    }
}
