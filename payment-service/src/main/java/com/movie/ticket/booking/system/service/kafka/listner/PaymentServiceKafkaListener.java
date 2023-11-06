package com.movie.ticket.booking.system.service.kafka.listner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.ticket.booking.system.commons.dto.BookingDTO;
import com.movie.ticket.booking.system.service.IPaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceKafkaListener {

    private final IPaymentService paymentService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "payment-request",groupId = "paymentRequest")
    public void subscribeFromPaymentRequestTopic(String bookingDTOJson){
        log.info("Received booking details from the payment-request kafka topic in payment service");
        try {
            BookingDTO bookingDTO= objectMapper.readValue(bookingDTOJson,BookingDTO.class);
            this.paymentService.processPayment(bookingDTO);
        } catch (JsonProcessingException e) {
            log.error("Error While receiving the booking details from the " +
                    "payment-request kafka topic in payment-service");
        }

    }
}
