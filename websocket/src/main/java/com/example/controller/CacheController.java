package com.example.controller;

import com.example.domain.Person;
import com.example.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController {
    @Autowired
    private PersonService personService ;

    @RequestMapping("/person/add")
    public Person addPerson(  Person person){
        return personService.save(person);
    }

    @RequestMapping("/person/del/{id}")
    public String  remove(@PathVariable("id") Long id ){
        personService.remove(id);
        return "delete success";
    }

    @RequestMapping("/person/show")
    public Person findPerson( Person person){
      return   personService.findOne(person);
    }


}
