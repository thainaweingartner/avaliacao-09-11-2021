package com.avaliacao.backend.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByEmail(String email);
    Person findByPhone(String phone);
    List<Person> findAllContacts(Long personId);
    void deleteContact(Long contactId, Long personId)
}
