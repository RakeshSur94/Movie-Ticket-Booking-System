package com.movie.ticket.booking.system.commons;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }
    @Bean
    public ObjectMapper createObjectMapper(){
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }


}