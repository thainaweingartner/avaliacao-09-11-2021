package com.avaliacao.backend.repositories;

import com.avaliacao.backend.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}
