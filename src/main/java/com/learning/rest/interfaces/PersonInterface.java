package com.learning.rest.interfaces;
import com.learning.rest.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonInterface extends JpaRepository<Person, Long> {
}