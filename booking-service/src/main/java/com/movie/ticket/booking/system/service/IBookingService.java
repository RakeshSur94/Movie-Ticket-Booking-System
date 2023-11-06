package com.movie.ticket.booking.system.service;

import com.movie.ticket.booking.system.commons.dto.BookingDTO;

public interface IBookingService {
    public BookingDTO createBooking(BookingDTO bookingDTO);
    public void processBooking(BookingDTO bookingDTO);
}
