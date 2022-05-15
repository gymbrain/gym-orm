package org.gymbrain.orm.repository;

import org.gymbrain.orm.common.repository.BaseRepository;
import org.gymbrain.orm.common.repository.GymRepository;
import org.gymbrain.orm.entity.Person;

@GymRepository
public interface PersonRepository extends BaseRepository<Person> {
}
