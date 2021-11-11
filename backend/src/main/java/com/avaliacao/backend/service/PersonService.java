package com.avaliacao.backend.service;

import com.avaliacao.backend.entities.Person;
import com.avaliacao.backend.exceptions.AlreadyExistsException;
import com.avaliacao.backend.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public Person save(Person person) {
        if(personRepository.existsByEmail(person.getEmail())) {
            throw new AlreadyExistsException("This email is already registered");
        }
        if(personRepository.existsByPhone(person.getPhone())) {
            throw new AlreadyExistsException("This phone is already registered");
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

        personFound.setEmail(person.getEmail());
        personFound.setName(person.getName());
        personFound.setPhone(person.getPhone());
        personRepository.save(personFound);
        return personFound;
    }

    public void delete(Long personId){
        personRepository.findById(personId)
                .orElseThrow(()-> new RuntimeException("Person not found"));
        personRepository.deleteById(personId);
    }
}
