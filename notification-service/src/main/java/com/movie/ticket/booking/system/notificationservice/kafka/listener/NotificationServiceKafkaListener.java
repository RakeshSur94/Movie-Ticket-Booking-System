package com.movie.ticket.booking.system.notificationservice.kafka.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.ticket.booking.system.commons.dto.BookingDTO;
import com.movie.ticket.booking.system.notificationservice.service.INotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationServiceKafkaListener {
    @Autowired
    private INotificationService notificationService;
    @Autowired
    private ObjectMapper objectMapper;
    @KafkaListener(topics = "payment-response",groupId = "email-notification")
    public void subscribePaymentResponseTopic(String bookingDTOJson){
        log.info("receiving conformation of booking details from payment-response kafka topic");
        try {
            BookingDTO bookingDTO = objectMapper.readValue(bookingDTOJson, BookingDTO.class);
            try {
                log.info("Sending the conformation mail to user!!!");
                this.notificationService.sendConformationMail(bookingDTO);
            } catch (Exception e) {
                log.error("Error while sending the mail..");
            }

        } catch (JsonProcessingException e) {
            log.error("Error while receiving conformation of booking details" +
                    " from payment-response kafka topic");
        }

    }
}
