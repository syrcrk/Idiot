package com.crk.gift.api;

import com.crk.gift.model.Person;
import com.crk.gift.service.PersonService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {
    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }
    @PostMapping
    public void addPerson(@Valid @NotNull @RequestBody  Person person){
        System.out.println("ADD PERSON");
        service.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllZombie(){
        return service.selectAllPeople();
    }

    @GetMapping(path="{id}")
    public Person findPerson(@PathVariable("id") UUID id){
        return service.selectPersonByID(id).orElse(null);
    }
    @DeleteMapping(path="{id}")
    public void DeletePerson(@PathVariable("id")  UUID id){
        service.deletePersonByID(id);
    }
    @PutMapping(path="{id}")
    public void UpdatePerson(@PathVariable("id") UUID id,@Valid @NotNull @RequestBody Person person){
        service.updatePersonByID(id,person);
    }
}
