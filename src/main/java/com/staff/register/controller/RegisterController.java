package com.staff.register.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.staff.register.model.Person;
import com.staff.register.service.RegisterService;

@RestController
@RequestMapping("/api/person")
public class RegisterController {

    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        return ResponseEntity.ok(registerService.getAllPersons());
    }

    @PostMapping
    public ResponseEntity<String> addPerson(@RequestBody Person person) {
        registerService.addPerson(person);
        return new ResponseEntity<>(person.email() + " has been saved to register", HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> deletePerson(@RequestParam("email") String email) {
        registerService.deletePersonByEmail(email);
        return new ResponseEntity<>(email + " has been deleted from register", HttpStatus.OK);
    }
}
