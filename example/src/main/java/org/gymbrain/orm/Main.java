package org.gymbrain.orm;

import org.gymbrain.orm.common.scanner.RepositoryScanner;
import org.gymbrain.orm.entity.Person;
import org.gymbrain.orm.manager.GymConnector;
import org.gymbrain.orm.repository.PersonRepository;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        PersonRepository impl = RepositoryScanner.getImpl(PersonRepository.class);
        impl.save(new Person());

        Connection connection = GymConnector.getConnection();
        System.out.println(connection);
    }
}
