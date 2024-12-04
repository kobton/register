/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package com.staff.register.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.staff.register.exception.DuplicatePersonException;
import com.staff.register.exception.PersonNotFoundException;
import com.staff.register.model.Person;

public class RegisterServiceTest {

    @InjectMocks
    private RegisterService registerService;

    @Mock
    private Map<String, Person> registerRepo;

    private static Person testPerson;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        testPerson = new Person("test@email.com", "Test", "Testsson");
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test of addPerson method, of class RegisterService.
     */
    @Test
    public void testAddPerson() {
        registerService.addPerson(testPerson);

        // assertEquals("test@email.com", registerRepo.get("test@email.com").email());
        verify(registerRepo).put(testPerson.email(), testPerson);
    }

    @Test
    public void testAddPersonDuplicate() {

        when(registerRepo.containsKey("test@email.com")).thenReturn(true);

        assertThrows(DuplicatePersonException.class, () -> registerService.addPerson(testPerson));
    }

    @Test
    public void testGetAllPersons() {
        Person person1 = new Person("test1@email.com", "Test1", "Testsson1");
        Person person2 = new Person("test2@email.com", "Test2", "Testsson2");

        List personList = new ArrayList();
        personList.add(person1);
        personList.add(person2);

        when(registerRepo.values()).thenReturn(personList);

        List<Person> result = registerService.getAllPersons();

        assertEquals(2, result.size());
        assertEquals("Testsson2", result.get(1).lastname());

    }

    @Test
    public void deletePerson() {
        when(registerRepo.containsKey(any())).thenReturn(true);
        when(registerRepo.remove(any())).thenReturn(testPerson);

        Person result = registerService.deletePersonByEmail("test@email.com");

        assertEquals(result.email(), "test@email.com");
        assertEquals(registerRepo.size(), 0);
    }

    @Test
    public void testDeletePersonNotFound() {

        when(registerRepo.containsKey(any())).thenReturn(false);

        assertThrows(PersonNotFoundException.class, () -> registerService.deletePersonByEmail("email@notexist.com"));
    }

}