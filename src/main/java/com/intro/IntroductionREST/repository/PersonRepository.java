package com.intro.IntroductionREST.repository;

import com.intro.IntroductionREST.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
    Person save(Person person);
}