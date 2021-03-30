package com.crk.gift.service;

import com.crk.gift.dao.PersonDAO;
import com.crk.gift.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    private final PersonDAO personDAO;

    @Autowired
    public PersonService(@Qualifier("postgres") PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public int addPerson(Person p) {
        return personDAO.insertPerson(p);
    }

    public List<Person> selectAllPeople() {
        return personDAO.selectAllPerson();
    }

    public Optional<Person> selectPersonByID(UUID id) {
        return personDAO.selectPersonByID(id);
    }

    public int deletePersonByID(UUID id) {
        return personDAO.deletePersonById(id);
    }

    public int updatePersonByID(UUID id, Person person) {
        return personDAO.updatePersonById(id, person);
    }


}
