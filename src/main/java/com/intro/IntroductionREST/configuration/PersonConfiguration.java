package com.intro.IntroductionREST.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import com.intro.IntroductionREST.client.PersonClient;

@Configuration
public class PersonConfiguration {
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.intro.IntroductionREST.wsdl");
        return marshaller;
    }

    @Bean
    public PersonClient personClient(Jaxb2Marshaller marshaller) {
        PersonClient client = new PersonClient();
        client.setDefaultUri("http://localhost:8081/ws/person");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
