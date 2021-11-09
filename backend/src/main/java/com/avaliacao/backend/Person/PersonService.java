package com.avaliacao.backend.Person;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public Person save(Person person){
        if(personRepository.existsByEmail(person.getEmail())) {
            throw Error('This email is already registered');
        }
        if(personRepository.existsByPhone(person.getPhone())) {
            throw Error('This phone is already registered');
        }
        return personRepository.save(person);
    }

    public Person findById(Long personId){
        Person person =  personRepository.findById(personId)
                .orElseThrow(()-> new RuntimeException("Person not found"));
        return person;
    }

    public List<Person> index(){
        List<Person> people =  personRepository.findAll();
        return people;
    }

    public Person update(Long personId, Person person){
        Person personFound =  personRepository.findById(personId)
                .orElseThrow(()-> new RuntimeException("Person not found"));
        personFound.setEmail(person.getEmail());
        personFound.setName(person.getName());
        personFound.setPhone(person.getPhone());
        personRepository.save(personFound);
        return personFound;
    }

    public void delete(Long personId){
        Person personFound =  personRepository.findById(personId)
                .orElseThrow(()-> new RuntimeException("Person not found"));
        personRepository.deleteById(personId);
    }

    public Person addContact(Long contactId, Long personId){
        Person personFound =  personRepository.findById(personId)
                .orElseThrow(()-> new RuntimeException("Person not found"));
        Person contactFound =  personRepository.findById(personId)
                .orElseThrow(()-> new RuntimeException("Person to add not found"));
        personFound.setContacts(person.getContacts());
        personRepository.save(personFound);
        return personFound;
    }

    public List<Person> findAllContacts(Long personId){
        List<Person> contacts =  personRepository.findAllContacts(personId)
                .orElseThrow(()-> new RuntimeException("Person has no contacts"));
        return contacts;
    }

    public void findAllContacts(Long personId){
        personRepository.deleteContact(contactId, personId)
                .orElseThrow(()-> new RuntimeException("This contact is not on the contact list of this person"));
    }
}
