package com.crk.gift.dao;

import com.crk.gift.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("FakeDao")
public class FakePersonDataAccessService implements PersonDAO {
    private static List<Person> DB = new ArrayList<Person>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPerson() {
        return DB;
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> personMayBe = selectPersonByID(id);
        if (personMayBe.isPresent()) {
            DB.remove(personMayBe.get());
            return 1;
        }
        return 0;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        return selectPersonByID(id).map(p -> {
            int indexOfFound = DB.indexOf(person);
            if (indexOfFound >= 0) {
                DB.set(indexOfFound, person);
                return 1;
            } else {
                return 0;
            }
        }).orElse(0);
    }

    @Override
    public Optional<Person> selectPersonByID(UUID id) {
        return DB.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }
}
