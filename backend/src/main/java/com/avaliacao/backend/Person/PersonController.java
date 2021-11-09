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

    //CRUD Person
    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person){
        Person person1 = personService.save(person);
        return new ResponseEntity<>(person1, HttpStatus.CREATED);
    }

    @GetMapping("/{personId}")
    public ResponseEntity<Person> findPersonById(@PathVariable("personId")){
        Person person = personService.findById(personId);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Person>> index(){
        List<Person> people = personService.findAll();
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @PutMapping("/update/{personId}")
    public ResponseEntity<Person> updatePerson(@PathVariable("personId"), Person person){
        Person person = personService.update(personId, person);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{personId}")
    public ResponseEntity<Person> deletePerson(@PathVariable("personId"){
        Person person = personService.delete(personId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //CRUD ContactList
    @PutMapping("/add/{contactId}")
    public ResponseEntity<Person> addToContactList(@PathVariable("contactId"), Long personId){
        Person person = personService.addContact(contactId, personId);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @GetMapping("/contacts/{personId}")
    public ResponseEntity<List<Person>> findAllContacts(){
        List<Person> contacts = personService.findAllContacts(personId);
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{contactId}")
    public ResponseEntity<Person> deletePerson(@PathVariable("contactId"), Long personId){
        Person person = personService.deleteContact(contactId, personId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
