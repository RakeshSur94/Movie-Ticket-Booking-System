package com.movie.ticket.booking.system.service.broker;

import com.movie.ticket.booking.system.commons.dto.BookingDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service")
public interface PaymentServiceBroker {

    @PostMapping("/payments")
    public BookingDTO makePayment(@RequestBody BookingDTO bookingDTO);
}
//key: sk_test_51J9QEfSJ251E1BhYfT5KAT2kF2Ht7Td8qTtVGbVVewjrN3k2pmeVG2tbwNd3csbiGUitkEayv0Pae829sVUHV3Vj00mDljVjOy