package com.intro.IntroductionREST.controller;

import com.intro.IntroductionREST.model.Person;
import com.intro.IntroductionREST.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RequestController {

    @Autowired
    PersonRepository repository;

    @PostMapping("/new-request")
    @ResponseBody
    public String processRequest(@RequestBody Person person) {
        repository.save(person);
        return person.toString();
    }
}