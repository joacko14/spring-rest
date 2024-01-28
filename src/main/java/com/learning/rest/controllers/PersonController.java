package com.learning.rest.controllers;

import com.learning.rest.interfaces.PersonInterface;
import com.learning.rest.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private PersonInterface personInterface;

    @PostMapping
    public Person newPerson(@RequestBody Person person) {
        return personInterface.save(person);
    }

    @GetMapping
    public List<Person> getAll() {
        return personInterface.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getById(@PathVariable Long id) {
        return personInterface.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person personDetails) {
        return personInterface.findById(id).map(currentPerson -> {
            currentPerson.setFirstName(personDetails.getFirstName());
            currentPerson.setLastName(personDetails.getLastName());
            currentPerson.setAge(personDetails.getAge());
            return ResponseEntity.ok(currentPerson);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Long id) {
        return personInterface.findById(id).map(person -> {
            personInterface.delete(person);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
