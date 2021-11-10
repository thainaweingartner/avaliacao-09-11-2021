package com.avaliacao.backend.Person;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public Person save(Person person) throws Exception {
        if(personRepository.existByEmail(person.getEmails())) {
            throw new Exception("This email is already registered");
        }
        if(personRepository.existByPhone(person.getPhones())) {
            throw new Exception("This phone is already registered");
        }
        return personRepository.save(person);
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
        List<Person> contacts = personRepository.findAllContacts(personId).orElseThrow(() -> new RuntimeException("Person has no contacts"));
        return contacts;
    }

    public void deleteContact(Long contactId, Long personId) throws Exception{
        personRepository.findContact(contactId, personId).orElseThrow(()-> new RuntimeException("This contact is not on the contact list of this person"));
        personRepository.deleteContact(contactId, personId);
    }
}
