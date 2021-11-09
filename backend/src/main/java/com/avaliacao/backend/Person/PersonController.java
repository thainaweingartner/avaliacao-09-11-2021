package com.avaliacao.backend.Person;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("person")
public class PersonController {
    @Autowired
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<Person> save(@RequestBody Person person){
        Person person1 = personService.save(person);
        return new ResponseEntity<>(person1, HttpStatus.CREATED);
    }

    @GetMapping("{personId}")
    public ResponseEntity<Person> findById(@PathVariable("personId") Long personId){
        Person person = personService.findById(personId);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }
}
