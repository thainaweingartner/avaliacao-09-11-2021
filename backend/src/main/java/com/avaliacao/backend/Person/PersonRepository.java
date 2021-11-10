package com.avaliacao.backend.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    boolean existByEmail(List<Email> email);
    boolean existByPhone(List<Phone> phone);
    Optional<Person> findContact(Long contactId, Long personId);
    Optional<List<Person>> findAllContacts(Long personId);
    void deleteContact(Long contactId, Long personId);
}
