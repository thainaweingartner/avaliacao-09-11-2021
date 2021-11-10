package com.avaliacao.backend.Person;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("person")
public class PersonController {
    @Autowired
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    //CRUD Person
    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) throws Exception {
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
    public ResponseEntity<Person> updatePerson(@PathVariable Long personId, Person person){
        Person personUpdated = personService.update(personId, person);
        return new ResponseEntity<>(personUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{personId}")
    public ResponseEntity.BodyBuilder deletePerson(@PathVariable Long personId)  {
        personService.delete(personId);
        return ResponseEntity.status(HttpStatus.OK);
    }

    //CRUD ContactList
    @PutMapping("/add/{contactId}")
    public ResponseEntity<Person> addToContactList(@PathVariable Long contactId, Long personId){
        Person person = personService.addContact(contactId, personId);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @GetMapping("/contacts/{personId}")
    public ResponseEntity<List<Person>> findAllContacts(@PathVariable Long personId) throws Exception {
        try {
            List<Person> contacts = personService.findAllContacts(personId);
            return new ResponseEntity<>(contacts, HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @DeleteMapping("/delete/{contactId}")
    public ResponseEntity.BodyBuilder deleteContact(@PathVariable Long contactId, Long personId) {
        try {
            personService.deleteContact(contactId, personId);
            return ResponseEntity.status(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
