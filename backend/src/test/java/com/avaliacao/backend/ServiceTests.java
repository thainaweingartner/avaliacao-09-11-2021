package com.avaliacao.backend;

import com.avaliacao.backend.entities.Person;
import com.avaliacao.backend.exceptions.AlreadyExistsException;
import com.avaliacao.backend.repositories.PersonRepository;
import com.avaliacao.backend.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceTests {

    private Person person;

    @Mock
    private PersonRepository personRepository;
    private PersonService personService;

    @BeforeEach
    void setUp(){
        personService = new PersonService(personRepository);
        person = PersonFactory.buildDefault();
    }

    @DisplayName("must create a person")
    @Test
    void create(){
        Person newPerson = PersonFactory.buildDefault();

        when(personRepository.save(newPerson)).thenReturn(person);

        Person createdUser = personService.save(newPerson);

        verify(personRepository, times(1)).save(person);
        assertEquals(createdUser, person);
    }

    @DisplayName("must not create a person if email already exists")
    @Test
    void notCreateWithConflictEmail(){
        Person personRepeated = PersonFactory.buildDefault();
        when(personRepository.existsByEmail(personRepeated.getEmail())).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> personService.save(personRepeated));
        verify(personRepository, times(0)).save(personRepeated);
        verify(personRepository, times(1)).existsByEmail(personRepeated.getEmail());
    }

    @DisplayName("must not create a person if phone number already exists")
    @Test
    void notCreateWithConflictPhone(){
        Person personRepeated = PersonFactory.buildDefault();
        when(personRepository.existsByEmail(personRepeated.getEmail())).thenReturn(false);
        when(personRepository.existsByPhone(personRepeated.getPhone())).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> personService.save(personRepeated));
        verify(personRepository, times(0)).save(personRepeated);
        verify(personRepository, times(1)).existsByEmail(personRepeated.getEmail());
        verify(personRepository, times(1)).existsByPhone(personRepeated.getPhone());
    }

    @DisplayName("Must find all Persons")
    @Test
    public void findAllPeople() {
        final List<Person> people = Collections.singletonList(person);

        when(personRepository.findAll()).thenReturn(people);

        final List<Person> listOfUsers = personService.findAll();

        verify(personRepository, times(1)).findAll();
        assertEquals(listOfUsers.iterator().next(), person);
    }

    @DisplayName("Must update a Person")
    @Test
    public void updatePerson() {
        Person personUpdated = PersonFactory.buildPersonUpdated();
        Person infoToUpdate = PersonFactory.buildPersonUpdateInfo();

        when(personRepository.findById(1L)).thenReturn(Optional.ofNullable(person));

        final Person newPerson = personService.update(1L, infoToUpdate);

        verify(personRepository, times(1)).findById(1L);
        assertEquals(newPerson, personUpdated);
    }

    @DisplayName("Must delete a Person")
    @Test
    public void deletePerson() {
        when(personRepository.findById(1L)).thenReturn(Optional.ofNullable(person));
        personService.delete(1L);
        verify(personRepository, times(1)).findById(1L);
        verify(personRepository, times(1)).deleteById(1L);
    }




}
