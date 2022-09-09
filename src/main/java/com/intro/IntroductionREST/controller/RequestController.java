package com.intro.IntroductionREST.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.intro.IntroductionREST.client.PersonClient;
import com.intro.IntroductionREST.model.Person;
import com.intro.IntroductionREST.repository.PersonRepository;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class RequestController {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    PersonClient personClient;

    @PostMapping(value = "/new-request", produces = "application/xml")
    @ResponseBody
    public String processRequest(@RequestBody Person person) throws Exception {
        //преобразуем json-запрос в xml через модель Person
        String personObject = XML.toString(new JSONObject(person), Person.class.getSimpleName().toLowerCase());

        try {
            personRepository.save(person);
        } catch (Exception e) {
            throw new Exception("There was an error during person creation process. Check your credentials and try again!");
        }

        //отправка запроса в soap-приложение
        String response = personClient.processPerson(personObject);

        //преобразуем ответ soap-приложения в модель Person и сохраняем в БД
        personRepository.save(personClient.mapPersonFromXML(response));

        return response;
    }
}