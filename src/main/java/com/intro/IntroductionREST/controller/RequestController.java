package com.intro.IntroductionREST.controller;

import com.intro.IntroductionREST.client.PersonClient;
import com.intro.IntroductionREST.model.Person;
import com.intro.IntroductionREST.repository.PersonRepository;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class RequestController {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    PersonClient personClient;

    Logger logger = LoggerFactory.getLogger(RequestController.class);

    @PostMapping(value = "/new-request", produces = "application/xml")
    @ResponseBody
    public String processRequest(@RequestBody Person person) throws Exception {
        //преобразуем json-запрос в xml через модель Person
        String personObject = XML.toString(new JSONObject(person), Person.class.getSimpleName().toLowerCase());

        logger.info("Person in request: \n" + person.toString());

        try {
            personRepository.save(person);
            logger.info("Successfully saved person.");
        } catch (Exception e) {
            logger.error("Could not save person from request: \n" + person.toString());
            throw new Exception("There was an error during person creation process. Check your credentials and try again!");
        }

        logger.info("Sending xml representation of person to soap-application: \n" + person.toString());

        //отправка запроса в soap-приложение
        String response = personClient.processPerson(personObject);

        //преобразуем ответ soap-приложения в модель Person и сохраняем в БД
        personRepository.save(personClient.mapPersonFromXML(response));
        logger.info("Successfully saved person from response: \n" + response);

        return response;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex)
    {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}