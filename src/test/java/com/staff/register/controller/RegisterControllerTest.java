/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package com.staff.register.controller;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.staff.register.service.RegisterService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.staff.register.model.Person;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(RegisterController.class)
public class RegisterControllerTest {

    public RegisterControllerTest() {
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RegisterService registerService;

    private static Person testPerson = new Person("test@email.com", "Test", "Testsson");

    /**
     * Test of getAllPersons method, of class RegisterController.
     */
    @Test
    public void testGetAllPersons() throws Exception {

        // Mock the service response
        List<Person> mockUsers = Arrays.asList(testPerson);
        when(registerService.getAllPersons()).thenReturn(mockUsers);

        mockMvc.perform(get("/api/person").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].email").value("test@email.com"))
                .andExpect(jsonPath("$[0].firstname").value("Test"))
                .andExpect(jsonPath("$[0].lastname").value("Testsson"));
    }

}