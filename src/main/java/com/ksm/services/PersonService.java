/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.services;


import com.ksm.models.Person;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author user
 */
@Service
public class PersonService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${base.url}/person")
    private String URL;
    
    public List<Person> getAll() {
        ResponseEntity<List<Person>> response = restTemplate
                .exchange(URL, HttpMethod.GET, null, 
                new ParameterizedTypeReference<List<Person>>(){});
        
        return response.getBody();
    }
    
    public Person getById(Integer id) {
        ResponseEntity<Person> response = restTemplate
                .exchange(URL + "/" +id, HttpMethod.GET, null,
                        new ParameterizedTypeReference<Person>() {});
        return response.getBody();
    }
    
    public void create(Person person) {
        try {
            restTemplate.postForEntity(URL, person, Person.class);
        } catch (RestClientException e) {
            e.printStackTrace();
        }
    }
    
      public void update(Integer id, Person person) {
        try {
            restTemplate.put(URL + "/" + id, person);
        } catch (RestClientException e) {
            e.printStackTrace();
        }
    }
    
    public void delete(Integer id) {
        try {
            restTemplate.delete(URL + "/" + id);
        } catch (RestClientException e) {
            e.printStackTrace();
        }
    }
}
