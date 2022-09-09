package com.intro.IntroductionREST.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.intro.IntroductionREST.model.Person;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.intro.IntroductionREST.wsdl.ProcessPersonRequest;
import com.intro.IntroductionREST.wsdl.ProcessPersonResponse;

public class PersonClient extends WebServiceGatewaySupport {

    public String processPerson(String person) {
        ProcessPersonRequest request = new ProcessPersonRequest();
        request.setPerson(person);

        //Отправка запроса soap-приложению
        ProcessPersonResponse response = (ProcessPersonResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8081/ws", request);

        return response.getPerson();
    }

    public Person mapPersonFromXML(String xml) throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.readValue(xml, Person.class);
    }
}