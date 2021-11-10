package com.avaliacao.backend.service;

import com.avaliacao.backend.entities.Person;
import com.avaliacao.backend.dto.PersonDTO;
import com.avaliacao.backend.repositories.ContactListRepository;
import com.avaliacao.backend.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    private ContactListRepository contactListRepository;

    public Person save(PersonDTO person) throws Exception {
        if(personRepository.existByEmail(person.getEmail())) {
            throw new Exception("This email is already registered");
        }
        if(personRepository.existByPhone(person.getPhone())) {
            throw new Exception("This phone is already registered");
        }
        return personRepository.save(Person.from(person));
    }

    public Person findById(Long personId){
        Person person =  personRepository.findById(personId)
                .orElseThrow(()-> new RuntimeException("Person not found"));
        return person;
    }

    public List<Person> findAll(){
        List<Person> people =  personRepository.findAll();
        return people;
    }

    public Person update(Long personId, Person person){
        Person personFound =  personRepository.findById(personId)
                .orElseThrow(()-> new RuntimeException("Person not found"));
        personFound.setEmails(person.getEmails());
        personFound.setName(person.getName());
        personFound.setPhones(person.getPhones());
        personRepository.save(personFound);
        return personFound;
    }

    public void delete(Long personId){
        personRepository.findById(personId)
                .orElseThrow(()-> new RuntimeException("Person not found"));
        personRepository.deleteById(personId);
    }

    public Person addContact(Long contactId, Long personId){
        Person personFound =  personRepository.findById(personId)
                .orElseThrow(()-> new RuntimeException("Person not found"));
        Person contactFound =  personRepository.findById(contactId)
                .orElseThrow(()-> new RuntimeException("Person to add not found"));

        List<Person> list = new ArrayList<>(Arrays.asList(contactFound));
        personFound.getContacts().setContacts(list);
        personRepository.save(personFound);
        return personFound;
    }

    public List<Person> findAllContacts(Long personId) throws Exception{
        List<Person> contacts = contactListRepository.findContacts(personId).orElseThrow(() -> new RuntimeException("Person has no contacts"));
        return contacts;
    }

    public void deleteContact(Long contactId, Long personId) throws Exception{
        contactListRepository.findContact(contactId, personId).orElseThrow(()-> new RuntimeException("This contact is not on the contact list of this person"));
        contactListRepository.deleteContact(contactId, personId);
    }
}
