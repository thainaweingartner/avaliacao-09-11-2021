package com.avaliacao.backend.repositories;

import com.avaliacao.backend.entities.Email;
import com.avaliacao.backend.entities.Person;
import com.avaliacao.backend.entities.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    boolean existByEmail(Email email);
    boolean existByPhone(Phone phone);
}
