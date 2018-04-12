package com.example.service;

import com.example.domain.Person;

public interface PersonService {

    public Person save(Person person);

    public void remove(Long id);

    public Person findOne(Person person);
}
