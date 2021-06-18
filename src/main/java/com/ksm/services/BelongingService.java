/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.services;

import com.ksm.models.Belonging;
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
public class BelongingService {
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${base.url}/person/belonging")
    private String URL;
    
    public List<Belonging> getAll() {
        ResponseEntity<List<Belonging>> response = restTemplate
                .exchange(URL, HttpMethod.GET, null, 
                new ParameterizedTypeReference<List<Belonging>>(){});
        return response.getBody();
    }
    
    public Belonging getById(Integer id) {
        ResponseEntity<Belonging> response = restTemplate
                .exchange(URL + "/" +id, HttpMethod.GET, null,
                        new ParameterizedTypeReference<Belonging>() {});
        return response.getBody();
    }
    
    public void create(Belonging belonging) {
        try {
            restTemplate.postForEntity(URL, belonging, Belonging.class);
        } catch (RestClientException e) {
            e.printStackTrace();
        }
    }
    
    public void update(Integer id, Belonging belonging) {
        try {
            restTemplate.put(URL + "/" + id, belonging);
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
