package com.movie.ticket.booking.system.service.api;

import com.movie.ticket.booking.system.commons.constants.LoggerConstants;
import com.movie.ticket.booking.system.commons.dto.BookingDTO;
import com.movie.ticket.booking.system.service.IBookingService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
@Slf4j
public class BookingApi {
    @Autowired
    private IBookingService bookingService;

    @PostMapping
    private BookingDTO createBooking(@Valid @RequestBody BookingDTO bookingDTO){
        log.info(LoggerConstants.ENTERED_CONTROLLER_MESSAGE.getValue(),"create Booking",this.getClass(),bookingDTO.toString());
       return this.bookingService.createBooking(bookingDTO);
    }
}
