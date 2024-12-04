package com.staff.register.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.staff.register.exception.DuplicatePersonException;
import com.staff.register.exception.PersonNotFoundException;
import com.staff.register.model.Person;

@Service
public class RegisterService {

    Map<String, Person> registerRepo = new HashMap();

    public Person addPerson(Person person) {

        if (registerRepo.containsKey(person.email())) {
            throw new DuplicatePersonException("this person email already exists in the register");
        }
        return registerRepo.put(person.email(), person);
    }

    public List<Person> getAllPersons() {
        return new ArrayList<>(registerRepo.values());
    }

    public Person deletePersonByEmail(String email) {
        if (!registerRepo.containsKey(email)) {
            throw new PersonNotFoundException("a person with this email does not exist in the register");
        }

        return registerRepo.remove(email);
    }

}
