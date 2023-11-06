package com.movie.ticket.booking.system.service.kafka.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.ticket.booking.system.commons.dto.BookingDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceKafkaPublisher {

    private final KafkaTemplate<String,String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void publishDataToPaymentResponseTopic(BookingDTO bookingDTO){
        log.info("publishing booking details to payment-response topic");
        try {
            this.kafkaTemplate.send("payment-response",objectMapper.writeValueAsString(bookingDTO));
        } catch (JsonProcessingException e) {
            log.error("Error while publishing booking details to payment-response topic");
        }

    }
}
