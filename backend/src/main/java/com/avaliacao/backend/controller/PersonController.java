package com.avaliacao.backend.controller;

import com.avaliacao.backend.entities.Person;
import com.avaliacao.backend.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
@CrossOrigin(origins = "*")
public class PersonController {
    @Autowired
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person person1 = personService.save(person);
        return new ResponseEntity<>(person1, HttpStatus.CREATED);
    }

    @GetMapping("/{personId}")
    public ResponseEntity<Person> findPersonById(@PathVariable Long personId){
        Person person = personService.findById(personId);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Person>> index(){
        List<Person> people = personService.findAll();
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @PutMapping("/update/{personId}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long personId, @RequestBody Person person){
        Person personUpdated = personService.update(personId, person);
        return new ResponseEntity<>(personUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{personId}")
    public ResponseEntity deletePerson(@PathVariable Long personId)  {
        personService.delete(personId);
        return ResponseEntity.noContent().build();
    }
}
