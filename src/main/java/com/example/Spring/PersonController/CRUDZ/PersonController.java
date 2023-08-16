package com.example.Spring.PersonController.CRUDZ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {

    @Autowired
    PersonRepository people;

    @RequestMapping(value = "/people", method = RequestMethod.POST)
    ResponseEntity<Person> createPerson(@RequestBody Person p){
       return new ResponseEntity<>(people.save(p), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.GET)
    ResponseEntity<Person> getPerson(@PathVariable Long id){
        if(!people.findById(id).isPresent()){
            return new ResponseEntity<>(people.findById(id).get(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(people.findById(id).get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/people", method = RequestMethod.GET)
    List<Person> getPersonList(){
        List<Person> personList = new ArrayList<>();
        people.findAll().forEach(personList::add);

        return personList;
    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.PUT)
    Person updatePerson(@RequestBody Person p){
        if(people.findById(p.getId()).isPresent()){
            return people.save(p);
        }
        return people.findById(p.getId()).get();

    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.DELETE)
    void DeletePerson(@PathVariable Long id){
        people.deleteById(id);
    }
}
