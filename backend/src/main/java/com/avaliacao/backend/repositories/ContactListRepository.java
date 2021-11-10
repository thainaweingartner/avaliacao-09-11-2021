package com.avaliacao.backend.repositories;

import com.avaliacao.backend.entities.ContactList;
import com.avaliacao.backend.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactListRepository extends JpaRepository<ContactList, Long> {
//    Optional<Person> findContact(Long contactId, Long personId);
//    Optional<List<Person>> findContacts(Long personId);
//    void deleteContact(Long contactId, Long personId);
}
